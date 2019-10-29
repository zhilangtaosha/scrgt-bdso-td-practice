import * as d3 from 'd3';
import * as dc from 'dc';
import * as crossfilter from 'crossfilter2';
import { Component, OnInit } from '@angular/core';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-data-salary',
  templateUrl: './data-salary.component.html',
  styleUrls: [
    '../date-graph.scss',
    './data-salary.component.scss'
  ]
})
export class DataSalaryComponent implements OnInit {

  constructor(private backendService: BackendService) { }

  ngOnInit() {
    this.backendService.getCelebritySalaryData()
      .subscribe(salaryData => {
      const parseTime = d3.timeParse('%Y');
      const chart = dc.compositeChart('#chart-2');
      const ndx = crossfilter();
      for (let i = 0; i < salaryData.length; i++) {
        ndx.add(salaryData.map(d => {
          var data = { x : parseTime(d.year) };
          for (let j = 0; j < salaryData.length; j++) {
            data['y'+j] = 0;
          }
          data['y'+i] = d.salary;
          return data;
        }));
      }
      const dim  = ndx.dimension(dc.pluck('x'));
      const groupArr = [];
      for (let i = 0; i < salaryData.length; i++) {
        groupArr.push(dim.group().reduceSum(dc.pluck('y' + i)));
      }
      const composeArr = [];
      const color = d3.scaleSequential().domain([0, salaryData.length]).interpolator(d3.interpolateRainbow); // Color for 100 celebs
      for (let i = 0; i < salaryData.length; i++) {
        composeArr.push(
            dc.lineChart(chart)
            .dimension(dim)
            .colors(color(i))
            .group(groupArr[i], salaryData[i].year));
      }
      chart
          .width(800)
          .height(550)
          .margins({top: 50, right: 50, bottom: 150, left: 50})
          .x(d3.scaleTime().domain([parseTime(2009), parseTime(2019)]))
          .yAxisLabel('Salary in Millions (USD)')
          .xAxisLabel('Year')
          .legend(
            dc.legend()
            .horizontal(true)
            .legendWidth(768)
            .itemWidth(130)
            .y(450)
            .x(100))
          .renderHorizontalGridLines(true)
          .compose(composeArr)
          .brushOn(false)
          .render();
    });
  }
}
