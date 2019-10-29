import { Component, OnInit } from '@angular/core';
import { Observable, of, from } from 'rxjs';
import { BackendService } from '../../services/backend.service';
import { SummaryCounts } from 'src/app/models/models';

@Component({
  selector: 'app-data-overview',
  templateUrl: './data-overview.component.html',
  styleUrls: ['./data-overview.component.scss']
})
export class DataOverviewComponent implements OnInit {

  boxOfficeString = '$0';
  filmsTotal: SummaryCounts;

  constructor(private backendService: BackendService) { }

  ngOnInit() {
    this.backendService.getFilmsTotal().subscribe(total => {
      this.filmsTotal = total;
    },
    e => console.log(e));
  }

  // private incrementCelebs(increment, max) {
  //   setTimeout(() => {
  //     if (this.filmsTotal.movieCount + increment < max) {
  //       this.filmsTotal.movieCount += increment;
  //       this.incrementCelebs(increment, max);
  //     } else {
  //       this.filmsTotal.movieCount = max;
  //     }
  //   }, 10);
  // }

  // private incrementMoreCelebs(increment, max) {
  //   setTimeout(() => {
  //     if (this.filmsTotal.multipleActorCount + increment < max) {
  //       this.filmsTotal.multipleActorCount += increment;
  //       this.incrementMoreCelebs(increment, max);
  //     } else {
  //       this.filmsTotal.multipleActorCount = max;
  //     }
  //   }, 10);
  // }

  // private incrementBoxOffice(increment, max) {
  //   setTimeout(() => {
  //     if (this.filmsTotal.totalBoxOffice + increment < max) {
  //       this.filmsTotal.totalBoxOffice += increment;
  //       this.boxOfficeString = '$' + this.shorten(this.filmsTotal.totalBoxOffice);
  //       this.incrementBoxOffice(increment, max);
  //     } else {
  //       this.filmsTotal.totalBoxOffice = max;
  //       this.boxOfficeString = '$' + this.shorten(this.filmsTotal.totalBoxOffice);
  //     }
  //   }, 10);
  // }

  // private shorten(value) {
  //     var newValue = value.toString();
  //     if (value >= 1000) {
  //         var suffixes = ['', 'K', 'M', 'B','T'];
  //         var suffixNum = Math.floor( (''+value).length/3 );
  //         var shortValue = 0;
  //         for (var precision = 2; precision >= 1; precision--) {
  //             shortValue = parseFloat( (suffixNum != 0 ? (value / Math.pow(1000,suffixNum) ) : value).toPrecision(precision));
  //             var dotLessShortValue = (shortValue + '').replace(/[^a-zA-Z 0-9]+/g,'');
  //             if (dotLessShortValue.length <= 2) { break; }
  //         }
  //         newValue = shortValue.toFixed(1).toString() + suffixes[suffixNum];
  //     }
  //     return newValue;
  // }
}
