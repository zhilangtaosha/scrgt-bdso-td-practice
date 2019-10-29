import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { CelebrityChangelog, CelebrityDetails } from 'src/app/models/models';
import { environment} from '../../../../environments/environment';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CelebrityChangelogService {
  apiEndpoints = {
    celebrityChangelog: '/changelogs'
  };

  private _changelog = new BehaviorSubject<CelebrityChangelog[]>([]);
  readonly changeLog = this._changelog.asObservable();
  private logStore: {changeLog: CelebrityChangelog[]} = {changeLog: []};

  constructor(private readonly http: HttpClient) { }

  /**
   * Get the changelog for the given celebrity
   */
  getCelebrityChangelog(celebId) {
    return this.http.get<CelebrityChangelog[]>(`${environment.API_URL}${this.apiEndpoints.celebrityChangelog}/${celebId}`)
  }

  updateChangelog(changeLog: CelebrityChangelog[]) {
    this.logStore.changeLog = changeLog;
    this._changelog.next(Object.assign({}, this.logStore).changeLog);
  }

}
