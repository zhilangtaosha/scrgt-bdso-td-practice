import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { CelebritiesComponent } from './celebrities.component';
import { LoadingIndicatorComponent } from '../common/loading-indicator/loading-indicator.component';
import { SearchComponent } from './search/search.component';
import { AppPageComponent } from '../common/app-page/app-page.component';
import { CelebrityComponent } from './celebrity/celebrity.component';
import { SearchResultComponent } from './search-result/search-result.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { FormsModule } from '@angular/forms';
import { NewsfeedComponent } from './celebrity/newsfeed/newsfeed.component';
import { CelebrityChangelogComponent } from './celebrity/celebrity-changelog/celebrity-changelog.component';
import { BackendService } from '../services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SearchService } from '../services/search.service';
import { MaterialModule } from '../material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { KeycloakService } from 'keycloak-angular';
import { FakeKeycloak } from '../mock/fake-keycloak';
import { MdbIconComponent } from 'angular-bootstrap-md';

describe('CelebritiesComponent', () => {
  let component: CelebritiesComponent;
  let fixture: ComponentFixture<CelebritiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CelebritiesComponent, LoadingIndicatorComponent, SearchComponent, AppPageComponent, CelebrityComponent,
        SearchResultComponent, NewsfeedComponent, CelebrityChangelogComponent, MdbIconComponent ],
      imports: [ InfiniteScrollModule, MaterialModule, HttpClientTestingModule, FormsModule,
        BrowserAnimationsModule ],
      providers: [ BackendService, SearchService, { provide: KeycloakService, useClass: FakeKeycloak } ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CelebritiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it ('should know if a supervisor can edit', () => {
    inject([FakeKeycloak], (cloak: FakeKeycloak) => {
    const spy = spyOnProperty(cloak, 'instance').and.returnValue(
      { profile: {username: 'Tester'}, realmAccess: {roles: 'SUPERVISOR'}}
    );
    component.ngOnInit();
    expect(component.canEdit).toBeTruthy();
    });
  });
  it ('should know if a rando cannot edit', () => {
    component.ngOnInit();
    expect(component.canEdit).toBeFalsy();
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
