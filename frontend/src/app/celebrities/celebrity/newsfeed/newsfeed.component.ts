import { Component, OnInit, Input } from '@angular/core';
import { NewsFeedService } from './newsfeed.service';
import { CelebrityDetails } from 'src/app/models/models';

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.scss']
})
export class NewsfeedComponent implements OnInit {
  @Input() newsSource;
  @Input() celebName: string;

  mArticles: Array<any>;

  constructor(private newsapi: NewsFeedService) {
  }

  ngOnInit() {
      if (this.newsSource.source === 'New York Times') {
        this.searchNYT(this.celebName);
      }
      if (this.newsSource.source === 'News API') {
        this.searchAllNews(this.celebName);
      }
    }

  searchNYT(celeb: string) {
    this.newsapi.getNYT(celeb)
    .subscribe(
      data => (
        this.mArticles =
        data.response.docs.map(
          article => {
            console.log('article ', article);
            const result = {title: '', urlToImage: '', url: ''};
            result.title = article.snippet;
            result.urlToImage = 'https://static01.nyt.com/'.concat(article.multimedia[0].url);
            result.url = article.web_url;
            return result;
      })),
      error => (console.log(error))
      );
  }
  searchAllNews(celeb: string) {
    this.newsapi.getNewsApi(celeb)
    .subscribe(
      data => (
        this.mArticles =
          data.articles.map(
            article => {
              const result = {title: '', urlToImage: '', url: ''};
              result.title = article.title;
              result.urlToImage = article.urlToImage;
              result.url = article.url;
              return result;
      })),
      error => (console.log(error))
      );
  }

}
