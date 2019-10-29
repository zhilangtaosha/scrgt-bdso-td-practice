import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import {formatDate} from '@angular/common';



@Injectable()
export class SearchService {
  httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json'})};

  constructor(private readonly http: HttpClient) { }

  queryElastic(searchTerm) {
    return this.http.post<any>(`${environment.ELASTIC_URL}/imdbactor,wikiactor,moviedbactor/_search?pretty`, searchTerm, this.httpOptions)
      .pipe(
        map(data => {
          console.log('es data: ', data);
          return data;
        })
      );
  }
  buildQuery(queryParts) {
    const query = { size: 200, query: { query_string: {query: '', fuzziness: 'AUTO'}}};
    queryParts.forEach(element => {
      if (query.query.query_string.query !== '') {
        query.query.query_string.query = query.query.query_string.query + ' AND ';
      }
      if (element.type === 'age') {
        const generateRangeQuery = element.value.map(value => 'birthday:[' +
          formatDate(new Date().setUTCFullYear(new Date().getUTCFullYear() - value.to), 'yyyy-MM-dd', 'en') + ' TO ' +
          formatDate(new Date().setUTCFullYear(new Date().getUTCFullYear() - value.from), 'yyyy-MM-dd', 'en') + ']'
          + ' OR birthDate:[' +
          formatDate(new Date().setUTCFullYear(new Date().getUTCFullYear() - value.to), 'yyyy-MM-dd', 'en') + ' TO ' +
          formatDate(new Date().setUTCFullYear(new Date().getUTCFullYear() - value.from), 'yyyy-MM-dd', 'en') + ']'
        );
        generateRangeQuery.forEach(age => {
          if (query.query.query_string.query !== '') {
            query.query.query_string.query = query.query.query_string.query + ' OR ';
          }
          query.query.query_string.query = query.query.query_string.query + age;
        });
      } else if (element.type === 'bool') {
        query.query.query_string.query = query.query.query_string.query + 'gender: ' + element.value;
      } else if (element.type === 'name') {
        query.query.query_string.query = query.query.query_string.query + element.value;
      } else {
        element.value.forEach(term => {
          if (query.query.query_string.query !== '') {
            query.query.query_string.query = query.query.query_string.query + ' OR ';
          }
          query.query.query_string.query = query.query.query_string.query + term;
        });
      }
    });
    return query;
  }
}
