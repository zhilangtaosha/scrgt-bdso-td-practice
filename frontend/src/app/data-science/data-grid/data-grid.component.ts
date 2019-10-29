import { Component, OnInit } from '@angular/core';
import { Observable, of, from } from 'rxjs';
import { BackendService } from '../../services/backend.service';

@Component({
  selector: 'app-data-grid',
  templateUrl: './data-grid.component.html',
  styleUrls: ['./data-grid.component.scss']
})
export class DataGridComponent implements OnInit {

  data = [
    { year: 2019, boxOffice: 0, movieCount: 0 },
    { year: 2018, boxOffice: 0, movieCount: 0 },
    { year: 2017, boxOffice: 0, movieCount: 0 },
    { year: 2016, boxOffice: 0, movieCount: 0 },
    { year: 2015, boxOffice: 0, movieCount: 0 },
    { year: 2014, boxOffice: 0, movieCount: 0 },
    { year: 2013, boxOffice: 0, movieCount: 0 },
    { year: 2012, boxOffice: 0, movieCount: 0 },
    { year: 2011, boxOffice: 0, movieCount: 0 },
    { year: 2010, boxOffice: 0, movieCount: 0 },
    { year: 2009, boxOffice: 0, movieCount: 0 },
  ];

  constructor(private backendService: BackendService) { }

  ngOnInit() {
    this.backendService.getAnnualTotals().subscribe((data) => {
      this.data = data;
    });
  }

}
