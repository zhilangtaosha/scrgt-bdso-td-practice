import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { mergeMap, delay, materialize, dematerialize } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { celebritiesData, celebrityData, searchResult, filmsTotal, moreFilmsTotal, boxOfficeTotal,
  filmsData, celebritySalaryData, filmsByGenderData, salaryByGenderData, annualTotals, celebrityChangelog, summaryCount } from './api/backend';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if (!environment.USE_MOCK ) {
      return next.handle(request);
    }

    // wrap in delayed observable to simulate server api call
    return of(null)
      .pipe(
        mergeMap(() => {
          let response;
          let requestURL;
          // celebrity summary
          requestURL = environment.API_URL + '/celebrities/query/byActorIds?actors=1';
          response = fakeHit(request, requestURL, 'GET', 200, celebrityData);
          if (response !== null) { return  of(new HttpResponse(response)); }

          // celebrity summary
          requestURL = environment.API_URL + '/celebrities/query/summaryCounts';
          response = fakeHit(request, requestURL, 'GET', 200, summaryCount);
          if (response !== null) { return  of(new HttpResponse(response)); }

          // celebrity api data
          requestURL = environment.API_URL + '/celebrities/1';
          response = fakeHit(request, requestURL, 'GET', 200, celebrityData);
          if (response !== null) { return  of(new HttpResponse(response)); }

          // celebrity api data
          requestURL = environment.API_URL + '/celebrities/';
          response = fakeHit(request, requestURL, 'GET', 200, celebrityData);
          if (response !== null) { return  of(new HttpResponse(response)); }

          // celebrities api data
          requestURL = environment.API_URL + '/celebrities';
          response = fakeHit(request, requestURL, 'GET', 200, celebritiesData);
          if (response !== null) { return of(new HttpResponse(response)); }

          // search result
          requestURL = environment.API_URL + '/search?query=';
          response = fakeHit(request, requestURL, 'GET', 200, searchResult);
          if (response !== null) { return of(new HttpResponse(response)); }

          // films with top 100
          requestURL = environment.API_URL + '/filmsTotal';
          response = fakeHit(request, requestURL, 'GET', 200, filmsTotal);
          if (response !== null) { return of(new HttpResponse(response)); }

          // films with multiple top 100
          requestURL = environment.API_URL + '/moreFilmsTotal';
          response = fakeHit(request, requestURL, 'GET', 200, moreFilmsTotal);
          if (response !== null) { return of(new HttpResponse(response)); }

          // box office total of top 100
          requestURL = environment.API_URL + '/boxOfficeTotal';
          response = fakeHit(request, requestURL, 'GET', 200, boxOfficeTotal);
          if (response !== null) { return of(new HttpResponse(response)); }

          // films data result
          requestURL = environment.API_URL + '/filmsData';
          response = fakeHit(request, requestURL, 'GET', 200, filmsData);
          if (response !== null) { return of(new HttpResponse(response)); }

          // celebrity salary data result
          requestURL = environment.API_URL + '/celebritySalaryData';
          response = fakeHit(request, requestURL, 'GET', 200, celebritySalaryData);
          if (response !== null) { return of(new HttpResponse(response)); }

          // number of films by gender
          requestURL = environment.API_URL + '/filmsByGenderData';
          response = fakeHit(request, requestURL, 'GET', 200, filmsByGenderData);
          if (response !== null) { return of(new HttpResponse(response)); }

          // salary by gender
          requestURL = environment.API_URL + '/salaryByGenderData';
          response = fakeHit(request, requestURL, 'GET', 200, salaryByGenderData);
          if (response !== null) { return of(new HttpResponse(response)); }

          // annual box office and film totals
          requestURL = environment.API_URL + '/annualTotals';
          response = fakeHit(request, requestURL, 'GET', 200, annualTotals);
          if (response !== null) { return of(new HttpResponse(response)); }

          // celebrity changelog
          requestURL = environment.API_URL + '/changelogs/1';
          response = fakeHit(request, requestURL, 'GET', 200, celebrityChangelog);
          if (response !== null) { return  of(new HttpResponse(response)); }

          // pass through any requests not handled above
          return next.handle(request);
        }),
        materialize(),
        delay(500),
        dematerialize()
      );
  }
}

function fakeHit(request, path, method, status, body) {
  if (request.url.indexOf(path) === 0 && request.method === method) {
    console.log(`Mock api call to: ${path}`);
    return {
      status,
      body
    };
  }
  return null;
}

export let fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};
