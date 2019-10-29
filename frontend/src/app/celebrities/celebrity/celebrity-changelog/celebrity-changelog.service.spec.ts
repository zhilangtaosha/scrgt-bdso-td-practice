import { TestBed, inject } from '@angular/core/testing';

import { CelebrityChangelogService } from './celebrity-changelog.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('CelebrityChangelogService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [ HttpClientTestingModule ],
    providers: [ CelebrityChangelogService ]
  }));

  it('should be created', inject([CelebrityChangelogService], (service: CelebrityChangelogService) => {
    expect(service).toBeTruthy();
  }));
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
