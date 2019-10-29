import * as d3 from 'd3';
import * as dc from 'dc';
import * as crossfilter from 'crossfilter2';
import { Component, OnInit } from '@angular/core';
import { Observable, of, from } from 'rxjs';
import { BackendService } from '../../services/backend.service';
import { FilmsByGender } from '../../models/models';

@Component({
  selector: 'app-data-gender',
  templateUrl: './data-gender.component.html',
  styleUrls: [
    '../date-graph.scss',
    './data-gender.component.scss'
  ]
})
export class DataGenderComponent implements OnInit {

  FILMS: FilmsByGender[];

  constructor(private backendService : BackendService) { }

  ngOnInit() {
    this.backendService.getFilmsByGenderData().subscribe(filmsData => {
      this.createChart(filmsData);
    });
  }

  createChart(filmsData) {
    this.FILMS = filmsData;
    this.FILMS.forEach(item => { item.gender+= ' Celebrities'; })
    var parseTime = d3.timeParse('%Y');
    this.FILMS.forEach(function(d) {
      d.year = parseTime(d.year); // Convert int to Date
    });
    var chart = dc.seriesChart('#chart-3');
    var ndx = crossfilter(this.FILMS),
      dim = ndx.dimension(function(d) {return [d.gender, +d.year]; }),
      group = dim.group().reduceSum(function(d) { return +d.movieCount; });
    chart
      .width(800)
      .height(500)
      .margins({top: 50, right: 50, bottom: 100, left: 50})
      .chart(function(c) { return dc.lineChart(c).curve(d3.curveCardinal); })
      .x(d3.scaleTime().domain([parseTime(2009),parseTime(2019)]))
      .brushOn(false)
      .yAxisLabel('Films (in Thousands)')
      .xAxisLabel('Year')
      .clipPadding(10)
      .elasticY(true)
      .dimension(dim)
      .group(group)
      .mouseZoomable(true)
      .ordinalColors(['pink', 'blue'])
      .seriesAccessor(function(d) {return d.key[0];})
      .valueAccessor(function(d) {return d.value;})
      .keyAccessor(function(d) {return +d.key[1];})
      .legend(dc.legend().x(300).y(450).itemHeight(13).gap(5).horizontal(1).legendWidth(400).itemWidth(150));
    dc.renderAll();
  }
}
