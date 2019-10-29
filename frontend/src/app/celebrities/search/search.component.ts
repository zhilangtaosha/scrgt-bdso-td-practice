import { Component, EventEmitter, Output, Input } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchFieldParams } from '../../models/models'; // , SearchResults
import { SearchService } from '../../services/search.service';
import { BackendService } from 'src/app/services/backend.service';
import { finalize } from 'rxjs/operators';
import { formatDate } from '@angular/common';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {

  @Input() canEdit: boolean;
  @Output() search: EventEmitter<{}> = new EventEmitter<boolean>();

  searchResults: any;
  rawResults: any;
  queryParams = '';
  lastSearch: string;

  constructor(private elasticServ: SearchService, private backendService: BackendService) {}

  showSearchResult = false;
  age = new FormControl();
  ageList: string[] = [
    ' Under 12 Year old ',
    ' 12- 17 Years Old ',
    ' 18- 24 Years Old ',
    ' 25- 29 Years Old ',
    ' 30- 45 Years Old ',
    ' 46- 60 Years Old ',
    ' 61- 74 Years Old ',
    ' 75+ Years Old '
  ];
  ageLookup = {
    ' Under 12 Year old ': {from: 0, to: 11},
    ' 12- 17 Years Old ': {from: 12, to: 17},
    ' 18- 24 Years Old ': {from: 28, to: 24},
    ' 25- 29 Years Old ': {from: 25, to: 29},
    ' 30- 45 Years Old ': {from: 30, to: 12},
    ' 46- 60 Years Old ': {from: 46, to: 60},
    ' 61- 74 Years Old ': {from: 61, to: 74},
    ' 75+ Years Old ': {from: 75, to: 120}
};

  genre = new FormControl();
  genreList: string[] = [
    ' Versatile* (default) ',
    ' Action ',
    ' Comedy ',
    ' Drama ',
    ' Thriller ',
    ' Sci-Fi '
  ];

  gender = new FormControl();
  genderList: string[] = [' Male ', ' Female '];
  genderLookup = {' Male ': 2, ' Female ': 1};

  selectedValue = '';

  selectedList: SearchFieldParams = {
    names: '',
    experienceLevel: [],
    ageRange: [],
    genre: [],
    gender: ''
  };

  searchValues = [];

  onSearch(searchInput: string) {

    this.searchValues = [];
    this.lastSearch = searchInput;
    console.log('search triggered');
    if (null != this.selectedList) {
      Object.keys(this.selectedList).forEach(element => {
        if (this.selectedList[element] !== [] && this.selectedList[element] !== '') {
          // if (element === 'names') {
          //   // this.searchValues.push({type: 'name', value: this.selectedList[element]});
          // } else
          if (element === 'gender') {
            const merged = this.selectedList[element];
            this.searchValues.push({type: 'bool', value: this.genderLookup[merged]});
          } else if (element === 'ageRange') {
            if (this.selectedList[element].length !== 0) {
              const merged = [].concat.apply([], this.selectedList[element]);
              this.searchValues.push({type: 'age', value: merged.map(ageElement => this.ageLookup[ageElement])});
            }
          } else {
            if (this.selectedList[element].length !== 0) {
              const merged = [].concat.apply([], this.selectedList[element]);
              this.searchValues.push({type: element, value: merged});
            }
          }
        }
      });
    }
    if (searchInput !== '') {
      this.searchValues.push({type: 'name', value: searchInput});
    }

    const elasticQuery = this.elasticServ.buildQuery(this.searchValues);
    console.log('es search query', elasticQuery);
    this.elasticServ.queryElastic(elasticQuery)
      .pipe(
        finalize(() => {
          this.getSearchResults(this.queryParams);
        })
      )
      .subscribe(
        data => {
          this.rawResults = data.hits.hits;
          const resultList = [];
          this.rawResults.forEach(searchItem => {
            if (resultList.indexOf('actors=' + searchItem._id) === -1) {
              resultList.push('actors=' + searchItem._id);
            }
          });
          console.log(resultList);
          this.queryParams = resultList.join('&');
          console.log(this.queryParams);
        },
        error => (console.log(error))
        );
  }

  getSearchResults(queryParams) {
    this.backendService.getCelebritiesByActorsIds(queryParams)
    .subscribe( actors => {
      this.searchResults = actors;
      console.log('search result: ', this.searchResults);
      let dataPresent = false;
      if (this.searchResults.length > 0 ) {
        dataPresent = true;
      }
      this.search.emit({showResults: true, result: { hasData: dataPresent, data: this.searchResults}});
    });
  }

  // selectionChange(event: any, selection: string) {
  //   this.selectedValue = '';
  //   switch (selection) {
  //     case 'gender':
  //       if (this.selectedList.gender !== event.value) {
  //         this.selectedList.gender = event.value;
  //       } else {
  //         this.selectedList.gender = '';
  //       }
  //       break;
  //     case 'ageRange':
  //       if (this.selectedList.ageRange !== event.value) {
  //         this.selectedList.ageRange = event.value;
  //       } else {
  //         this.selectedList.ageRange = [];
  //       }
  //       break;
  //     // case 'experienceLevel':
  //     //   if (this.selectedList.experienceLevel !== event.value) {
  //     //     this.selectedList.experienceLevel = event.value;
  //     //   } else {
  //     //     this.selectedList.experienceLevel = [];
  //     //   }
  //     //   break;
  //     case 'genre':
  //       if (this.selectedList.genre !== event.value) {
  //         this.selectedList.genre = event.value;
  //       } else {
  //         this.selectedList.genre = [];
  //       }
  //       break;
  //     default:
  //   }
  //   // if (this.selectedList.experienceLevel.length > 0) {
  //   //   this.selectedValue += this.getSelectedValue(
  //   //     this.selectedList.experienceLevel
  //   //   );
  //   // }
  //   if (this.selectedList.ageRange.length > 0) {
  //     this.selectedValue += this.getSelectedValue(this.selectedList.ageRange);
  //   }
  //   if (this.selectedList.genre.length > 0) {
  //     this.selectedValue += this.getSelectedValue(this.selectedList.genre);
  //   }
  //   if (this.selectedList.gender !== '') {
  //     this.selectedValue += this.selectedList.gender;
  //   }
  //   this.onSearch(this.lastSearch);
  // }

  getSelectedValue(list: any) {
    return list.map(x => x).join(' + ');
  }
}
