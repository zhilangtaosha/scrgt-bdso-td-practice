import * as d3 from 'd3';
import * as dc from 'dc';
import * as crossfilter from 'crossfilter2';
import { Component, OnInit } from '@angular/core';
import { Observable, of, from } from 'rxjs';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-data-year',
  templateUrl: './data-year.component.html',
  styleUrls: ['../date-graph.scss']
})
export class DataYearComponent implements OnInit {

  constructor(private backendService : BackendService) { }

  ngOnInit() {
    this.backendService.getFilmsData().subscribe(filmData => {
      let totalFilms = filmData.map((ele) => { return ele.movieCount }).reduce((a,b) => a + b); // Sums up films
      var chart = dc.pieChart('#chart-1');
      var ndx           = crossfilter(filmData),
        yearDimension  = ndx.dimension(function(d) {return d.year;}),
          filmGroup = yearDimension.group().reduceSum(function(d) {return d.movieCount;});
          chart
              .width(800)
              .height(800)
              .innerRadius(85)
              .externalLabels(30)
              .externalRadiusPadding(180)
              .drawPaths(true)
              .dimension(yearDimension)
              .group(filmGroup)
              .legend(
                dc.legend()
                .horizontal(true)
                .legendWidth(800)
                .itemWidth(200)
                .y(700))
              .minAngleForLabel(0);
          chart.on('pretransition', function(chart) {
            chart.selectAll('.dc-legend-item text')
                .text('')
              .append('tspan')
                .attr('x', 20)
                .text(function(d) { return d.name; })
              .append('tspan')
                .attr('text-anchor', 'start')
                .attr('x', 70)
                .text(function(d) { return d.data + '    (' + dc.utils.printSingleValue((d.data / totalFilms) * 100) + '%)'; }); // Percentage
          });
          chart.render();
      // Sorts legend (NOTE: Only sorts when graph is clicked)
      // dc.override(chart, 'legendables', () => {
      //     var legendables = chart._legendables();
      //     return legendables.sort((a,b) => { if (a.name > b.name) return 1; if (a.name < b.name) return -1; return 0; });
      // });
      // Changes legend to circles (NOTE: Reverts back when clicked)
      // let legendItems = document.querySelectorAll('.dc-legend-item rect');
      // legendItems.forEach((item) => {
      //   item.setAttribute('rx', '50%');
      // });
    });
  }
}

interface Film {
  year: number;
  films: number;
}
