import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeComponent } from './home.component';
import { RouterTestingModule } from '@angular/router/testing';
import { KeycloakService } from 'keycloak-angular';
import { FakeKeycloak } from '../mock/fake-keycloak';
import { DataScienceComponent } from '../data-science/data-science.component';
import { CelebritiesComponent } from '../celebrities/celebrities.component';
import { DataGridComponent } from '../data-science/data-grid/data-grid.component';
import { DataGenderPayComponent } from '../data-science/data-gender-pay/data-gender-pay.component';
import { DataOverviewComponent } from '../data-science/data-overview/data-overview.component';
import { DataYearComponent } from '../data-science/data-year/data-year.component';
import { DataSalaryComponent } from '../data-science/data-salary/data-salary.component';
import { DataGenderComponent } from '../data-science/data-gender/data-gender.component';
import { CelebrityChangelogComponent } from '../celebrities/celebrity/celebrity-changelog/celebrity-changelog.component';
import { SearchResultComponent } from '../celebrities/search-result/search-result.component';
import { LoadingIndicatorComponent } from '../common/loading-indicator/loading-indicator.component';
import { SearchComponent } from '../celebrities/search/search.component';
import { AppPageComponent } from '../common/app-page/app-page.component';
import { CelebrityComponent } from '../celebrities/celebrity/celebrity.component';
import { NewsfeedComponent } from '../celebrities/celebrity/newsfeed/newsfeed.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { MatProgressSpinnerModule } from '@angular/material';
import { MaterialModule } from '../material.module';
import { FormsModule } from '@angular/forms';
import { MdbIconComponent } from 'angular-bootstrap-md';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeComponent, DataScienceComponent, DataOverviewComponent, DataYearComponent, DataSalaryComponent,
        DataGenderComponent, DataGenderPayComponent, DataGridComponent, CelebritiesComponent, LoadingIndicatorComponent,
        SearchComponent, AppPageComponent, CelebrityComponent, SearchResultComponent, NewsfeedComponent, CelebrityChangelogComponent,
         MdbIconComponent ],
      imports: [ FormsModule, RouterTestingModule.withRoutes([ {path: 'data', component: DataScienceComponent},
      {path: 'celebrities', component: CelebritiesComponent}]), InfiniteScrollModule, MatProgressSpinnerModule, MaterialModule, ],
      providers: [ { provide: KeycloakService, useClass: FakeKeycloak } ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
