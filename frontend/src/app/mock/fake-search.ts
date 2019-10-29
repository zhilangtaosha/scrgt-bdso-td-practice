import { Injectable } from '@angular/core';
import { SearchService } from '../services/search.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';

@Injectable()
export class FakeSearch extends SearchService {
  constructor(http: HttpClient) {
      super(http);
  }
  queryElastic(searchTerm) {
    return Observable.of({data: {hits: {hits: [{_id: '1', _type: 'Actor',
        _source: {name: searchTerm, birthday: '12-15-1988', gender: 2 }}]}}});
  }
}
