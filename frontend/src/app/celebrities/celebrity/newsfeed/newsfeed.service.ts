import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NewsFeedService {

  NYT_KEY = 'AWAipBBEdFRegsvLd9L5AmOtl2y4shqz';
  NEWSAPI_KEY = '33045778a12c4c5d894198451ab6963f';
  httpOptions = {
    observe: 'response' as 'body'
  };

  constructor(private http: HttpClient) { }

  getNYT(celeb: string): Observable<any> {
    return this.http.get('https://api.nytimes.com/svc/search/v2/articlesearch.json?q=' + celeb + '&sort=newest&p=0&api-key=' + this.NYT_KEY)
    .pipe(
      map(data => {
        return data;
      })
    );
  }
  getNewsApi(celeb: string): Observable<any> {
    return this.http.get('https://newsapi.org/v2/everything?q=' + encodeURI(celeb) + '&apiKey=' + this.NEWSAPI_KEY)
    .pipe(
      map(data => {
        return data;
      })
    );
  }
}
