import * as dc from 'dc';
import * as crossfilter from 'crossfilter2';
import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  OnChanges,
  ChangeDetectorRef,
  SimpleChanges
} from '@angular/core';

import { CelebrityDetails, FacialRecognitionData, CelebrityChangelog, Movies } from '../../models/models';
import { CelebrityChangelogService } from './celebrity-changelog/celebrity-changelog.service';
import { BackendService } from '../../services/backend.service';
import { finalize, first } from 'rxjs/operators';
import { mergeMap } from 'rxjs/operators';
import { delay } from '../../common/utils';

@Component({
  selector: 'app-celebrity',
  templateUrl: './celebrity.component.html',
  styleUrls: ['./celebrity.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CelebrityComponent implements OnInit, OnChanges {
  @Input() celebDetails: CelebrityDetails;
  recommendedByFace = [];
  recommendationByGenreStat = [];
  recommendationByGenrePlots = [];
  recommendationBySimilarBios = [];
  showSave = false;
  editing = false;
  fName: string;
  lName: string;
  name: string;
  hasMovies = false;
  hasNumOfMovies = false;
  queryParams1 = '';
  queryParams2 = '';
  queryParams3 = '';
  queryParams4 = '';

  @Input() canEdit;
  @Input() chartData;
  @Input() displayedRow;
  @Output() recommendation: EventEmitter<any> = new EventEmitter<any>();
  moviesList: Movies[];
  ourRoles: string[];

  constructor(
    private changelogService: CelebrityChangelogService,
    private backendService: BackendService,
    private ref: ChangeDetectorRef
  ) {}

  newsfeed = [
    {
      source: 'New York Times',
      logo: '../../assets/nyt.png',
      data: 'something'
    },
    {
      source: 'News API',
      logo: '../../assets/newsApiLogo.png',
      data: 'something'
    }
  ];

  ngOnChanges(changes: SimpleChanges) {
    console.log('celebrity details ', this.celebDetails);
    try {
      if (!this.celebDetails.filmography) {
        this.hasMovies = false;
      } else {
        try {
          if (this.celebDetails.filmography.movies) {
            this.hasMovies = true;
          }
          if (this.celebDetails.filmography.listOfMoviesByYear) {
            this.getFilmData();
          }
          if (this.celebDetails.filmography.numberOfMovies) {
            this.hasNumOfMovies = true;
          }
        } catch (e) {
          console.log('filmography data incomplete, ', e);
        }
      }
    } catch (e) {
      this.hasMovies = false;
      console.log('undefined elements in ', this.celebDetails);
    }
  }

  ngOnInit() {

    this.ref.detectChanges();
    this.fName = this.celebDetails.firstName;
    this.lName = this.celebDetails.lastName;
    this.name = this.fName
      .charAt(0)
      .toUpperCase()
      .concat(
        this.fName.slice(1),
        ' ',
        this.lName.charAt(0).toUpperCase(),
        this.lName.slice(1)
      );
    this.getFacialRecognitionRecommendations();
    this.getGenreStatRecommendations();
    this.getGenrePlotsRecommendations();
    this.getSimilarBiosRecommendations();

    this.changelogService
      .getCelebrityChangelog(this.celebDetails.id)
      .subscribe(changelog => {
        this.changelogService.updateChangelog(changelog);
      });

    console.log(
      'data - ',
      this.recommendationBySimilarBios,
      this.recommendationByGenrePlots,
      this.recommendedByFace
    );
  }

  getFilmData() {
    (async () => {
      await delay(1000);
      console.log('after delay')
    })();
    let filmsData: any[];
    filmsData = this.celebDetails.filmography.listOfMoviesByYear;
    const chart = dc.pieChart('#celeb-chart');
    const ndx = crossfilter(filmsData);
    const yearDimension = ndx.dimension(d => d.year);
    const filmGroup = yearDimension.group().reduceSum(d => d.movieCount);
    chart
      .width(460)
      .height(400)
      .innerRadius(50)
      .externalRadiusPadding(40)
      .cx(260)
      .drawPaths(true)
      .dimension(yearDimension)
      .group(filmGroup)
      .legend(
        dc
          .legend()
          .horizontal(false)
          .itemWidth(60)
          .y(10)
      );
    chart.on('pretransition', c => {
      c.selectAll('.dc-legend-item text')
        .text('')
        .append('tspan')
        .attr('x', 20)
        .text(d => d.name)
        .append('tspan')
        .attr('text-anchor', 'start')
        .attr('x', 64)
        .text(d => d.data);
    });
    chart.render();
  }

  editData() {
    this.editing = true;
    this.showSave = true;
    this.canEdit = false;
  }

  saveData() {
    this.backendService
      .setCelebrityData(this.celebDetails)
      .pipe(
        mergeMap((celebrity: CelebrityDetails) => {
          return this.changelogService.getCelebrityChangelog(celebrity.id);
        })
      )
      .subscribe((changeLog: CelebrityChangelog[]) => {
        this.changelogService.updateChangelog(changeLog);
      });
    this.editing = false;
    this.showSave = false;
    this.canEdit = true;
  }

  onRecommendationClick(celebId: number, celebName: string) {
    const _INFO = { celebId, celebName };
    this.recommendation.emit(_INFO);
  }

  private getFacialRecognitionRecommendations() {
    this.backendService
      .getFacialRecommendation(this.name)
      .pipe(
        finalize(() => {
          this.backendService
            .getCelebritiesByActorsIds(this.queryParams1)
            .pipe(first())
            .subscribe(cd => {
              this.goAssign('recommendedByFace', cd);
              console.log('recommended faces', this.recommendedByFace);
            });
        })
      )
      .subscribe(d => {
        const data: FacialRecognitionData = d;
        const ids = [];
        data.results.forEach(result => {
          ids.push('actors='.concat(result._actorID));
        });
        this.queryParams1 = ids.join('&');
      });
  }

  private getGenreStatRecommendations() {
    this.backendService
      .getSimilarGenreStatsRecommendation(this.name)
      .pipe(
        finalize(() => {
          this.backendService
            .getCelebritiesByActorsIds(this.queryParams2)
            .pipe(first())
            .subscribe(cd => {
              this.goAssign('recommendationByGenreStat', cd);
            });
        })
      )
      .subscribe(d => {
        const data: FacialRecognitionData = d;
        const ids = [];
        data.results.forEach(result => {
          ids.push('actors='.concat(result._actorID));
        });
        this.queryParams2 = ids.join('&');
      });
  }

  private getGenrePlotsRecommendations() {
    this.backendService
      .getSimilarGenrePlotsRecommendation(this.name)
      .pipe(
        finalize(() => {
          this.backendService
            .getCelebritiesByActorsIds(this.queryParams3)
            .pipe(first())
            .subscribe(cd => {
              this.goAssign('recommendationByGenrePlots', cd);
            });
        })
      )
      .subscribe(d => {
        const data: FacialRecognitionData = d;
        const ids = [];
        data.results.forEach(result => {
          ids.push('actors='.concat(result._actorID));
        });
        this.queryParams3 = ids.join('&');
      });
  }

  private getSimilarBiosRecommendations() {
    this.backendService
      .getSimilarBiosRecommendation(this.name)
      .pipe(
        finalize(() => {
          this.backendService
            .getCelebritiesByActorsIds(this.queryParams4)
            .pipe(first())
            .subscribe(cd => {
              this.goAssign('recommendationBySimilarBios', cd);
            });
        })
      )
      .subscribe(d => {
        const data: FacialRecognitionData = d;
        const ids = [];
        data.results.forEach(result => {
          ids.push('actors='.concat(result._actorID));
        });
        this.queryParams4 = ids.join('&');
      });
  }

  private goAssign(give, value) {
    if (give === 'recommendedByFace') {
      this.recommendedByFace = value.splice(0, 5);
      this.ref.detectChanges();
    }
    if (give === 'recommendationByGenreStat') {
      this.recommendationByGenreStat = value.splice(0, 5);
      this.ref.detectChanges();
    }
    if (give === 'recommendationByGenrePlots') {
      this.recommendationByGenrePlots = value.splice(0, 5);
      this.ref.detectChanges();
    }
    if (give === 'recommendationBySimilarBios') {
      this.recommendationBySimilarBios = value.splice(0, 5);
      this.ref.detectChanges();
    }
  }
  matchRole() {
    this.celebDetails.filmography.movies.forEach(movie => {
      movie.cast.forEach(castMem => {
        if (this.celebDetails.id === castMem.celebrityId) {
          this.ourRoles.push(castMem.roleName);
        }
    });
  });
  }
}
