import { BrowserModule } from '@angular/platform-browser';
import {NgModule, NO_ERRORS_SCHEMA, APP_INITIALIZER } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from './material.module';

import { AppRoutingModule } from './app-routing.module';
import { fakeBackendProvider } from './mock/fake-backend';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ReplaceSpacePipe } from './common/pipes';
import { BackendService } from './services/backend.service';
import { SearchService } from './services/search.service';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { CookieService } from 'ngx-cookie-service';
import { CelebrityComponent} from './celebrities/celebrity/celebrity.component';
import { CelebritiesComponent } from './celebrities/celebrities.component';
import { SearchComponent } from './celebrities/search/search.component';
import { PlaceholderDirective } from './common/placeholder.directive';
import { LoadingIndicatorComponent } from './common/loading-indicator/loading-indicator.component';
import { SearchResultComponent } from './celebrities/search-result/search-result.component';
import { AppPageComponent } from './common/app-page/app-page.component';
import { DataScienceComponent } from './data-science/data-science.component';
import { DataOverviewComponent } from './data-science/data-overview/data-overview.component';
import { DataYearComponent } from './data-science/data-year/data-year.component';
import { DataSalaryComponent } from './data-science/data-salary/data-salary.component';
import { DataGridComponent } from './data-science/data-grid/data-grid.component';
import { DataGenderComponent } from './data-science/data-gender/data-gender.component';
import { DataGenderPayComponent } from './data-science/data-gender-pay/data-gender-pay.component';
import { CelebrityChangelogComponent } from './celebrities/celebrity/celebrity-changelog/celebrity-changelog.component';
import { NewsfeedComponent } from './celebrities/celebrity/newsfeed/newsfeed.component';
import { AppAuthGuard } from './services/auth.service';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';
import { initializer } from './common/app-init';
import { HistoryViewComponent } from './celebrities/celebrity/celebrity-changelog/history-view/history-view.component';
import { CelebrityChangelogService } from './celebrities/celebrity/celebrity-changelog/celebrity-changelog.service';
import {AutofocusDirective} from './common/auto-foucs.directive';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CelebrityComponent,
    CelebritiesComponent,
    SearchComponent,
    SearchResultComponent,
    PlaceholderDirective,
    LoadingIndicatorComponent,
    AppPageComponent,
    ReplaceSpacePipe,
    AppComponent,
    DataScienceComponent,
    DataSalaryComponent,
    DataOverviewComponent,
    DataYearComponent,
    DataGridComponent,
    DataGenderComponent,
    DataGenderPayComponent,
    CelebrityChangelogComponent,
    NewsfeedComponent,
    HistoryViewComponent,
    AutofocusDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MaterialModule,
    FlexLayoutModule,
    InfiniteScrollModule,
    MDBBootstrapModule.forRoot(),
    FormsModule,
    KeycloakAngularModule
  ],
  providers: [
    BackendService,
    fakeBackendProvider,
    CookieService,
    AppAuthGuard,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    },
    CelebrityChangelogService,
    SearchService
  ],
  entryComponents: [CelebrityComponent, HistoryViewComponent],
  schemas: [ NO_ERRORS_SCHEMA ],
  bootstrap: [AppComponent]
})
export class AppModule {}

