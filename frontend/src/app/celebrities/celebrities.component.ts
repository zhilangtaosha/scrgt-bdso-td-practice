import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {Celebrity, CelebrityDetails, ROLES, SearchResults} from '../models/models';
import { BackendService } from '../services/backend.service';
import { Observable } from 'rxjs';
import { trigger, transition, animate, style } from '@angular/animations';
import {KeycloakService} from 'keycloak-angular';


@Component({
  selector: 'app-celebrities',
  templateUrl: './celebrities.component.html',
  styleUrls: ['./celebrities.component.scss'],
  animations: [
    trigger('slideInOut', [
      transition(':enter', [
        style({transform: 'translateY(-100%)'}),
        animate('500ms ease-in', style({transform: 'translateY(0%)'}))
      ]),
      transition(':leave', [
        animate('500ms ease-in', style({transform: 'translateY(-100%)'}))
      ])
    ])
  ]
})
export class CelebritiesComponent implements OnInit {
  allCelebrities: Celebrity[] = [];
  celebItems: Celebrity[] = [];
  celebRows: Row[];
  searchResults: SearchResults;
  celebDetails: CelebrityDetails;

  displayedCeleb = '';
  displayId = -1;
  displayRow = -1;
  displayColumn = -1;
  celebId = -1;
  pagingStart = 0;
  itemsPerPage = 20;
  throttle = 300;
  scrollDistance = 2;
  scrollUpDistance = 1.5;
  showCelebDetail = false;
  showSearchResult = false;
  showAllCelebs = false;
  stateReady = false;
  canEdit = false;

  constructor(
    private backendService: BackendService,
    protected keycloakAngular: KeycloakService) {}

  ngOnInit() {
    this.showEveryCelebs();
    const userDetails = this.keycloakAngular.getKeycloakInstance().realmAccess.roles;
    if ( userDetails.includes(ROLES.SUPERVISOR)) {
      this.canEdit = true;
    }
  }

  onScrollDown(ev) {
    console.log('scrolled down!!', ev.currentScrollPosition);
    // if(this.movieService.pagingStart+5 < this.movieService.movieList.length) {
    // this.movieService.pagingStart = this.movieService.pagingStart + 5;
    this.itemsPerPage = this.itemsPerPage + 5;
    this.loadItems();
    // }
  }

  onUp(ev) {
    console.log('scrolled up!', ev.currentScrollPosition);
    /*if(this.movieService.pagingStart-5>0) {
      this.movieService.pagingStart = this.movieService.pagingStart - 5;
      this.loadData();
    }else{
      this.movieService.pagingStart=0;
      this.loadData();
    }
    console.log('scrolled up!', this.movieService.pagingStart);*/
  }

  showEveryCelebs() {
    this.backendService.getAllCelebritiesData().subscribe(data => {
      this.allCelebrities = data;
      this.showAllCelebs = true;
      this.stateReady = true;
      this.showSearchResult = !this.showAllCelebs;
      this.loadItems().subscribe(() => console.log('loading item'));
    });
  }

  async showDetail(
    celebId: number,
    celebName: string,
    row?: number,
    column?: number
  ) {
    if (this.displayId === celebId) {
      this.celebId = -1;
      this.showCelebDetail = false;
      this.displayedCeleb = '';
      this.displayId = -1;
      this.displayColumn = -1;
      this.displayRow = -1;
    } else {
      this.backendService.getCelebrityData(celebId).
        subscribe( d => {
          this.celebDetails = d;
          this.celebId = celebId;
          this.displayedCeleb = celebId + celebName;
          this.showCelebDetail = true;
          this.displayId = celebId;
          this.displayColumn = column;
          this.displayRow = row;
      });
    }
  }

  onSearch(event) {
    console.log('search resutls -- ', event);
    this.showSearchResult = event.showResults;
    this.showAllCelebs = false;
    this.searchResults = event.result;
    this.stateReady = true;
  }

  loadItems() {
    const pagingEnd = this.pagingStart + this.itemsPerPage;
    return new Observable(obs => {
      this.celebItems = [];
      if (pagingEnd < this.allCelebrities.length) {
        this.celebItems = this.allCelebrities.slice(
          this.pagingStart,
          pagingEnd
        );
      } else {
        this.celebItems = this.allCelebrities.slice(
          this.pagingStart,
          this.allCelebrities.length
        );
      }
      this.rows();
      obs.next({});
    });
  }

  rows() {
    this.celebRows = [];
    let row = null;
    for (let i = 0; i < this.allCelebrities.length; i++) {
      if (i % 4 === 0) {
        row = new Row();
        row.items = [];
        this.celebRows.push(row);
      }
      row.items.push(this.allCelebrities[i]);
    }
  }

  onRecommendationClicked(event) {
    const info = event;
    let row = 0;
    let column = 0;
    for (let i = 0; i < this.allCelebrities.length; i++) {
      if (i % 4 === 0 && i !== 0) {
        row ++;
      }
      if (this.allCelebrities[i].id === info.celebId) {
        column = i % 4;
        break;
      }
    }
    this.showCelebDetail = false;
    this.showDetail(info.celebId, info.celebName, row, column);
  }
}

class Row {
  items: CelebrityDetails[];
}
