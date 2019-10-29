import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { MaterialModule } from '../../material.module';
import { SearchComponent } from './search.component';
import { SearchService } from 'src/app/services/search.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SearchResultComponent } from '../search-result/search-result.component';
import { CelebrityComponent } from '../celebrity/celebrity.component';
import { FormsModule } from '@angular/forms';
import { NewsfeedComponent } from '../celebrity/newsfeed/newsfeed.component';
import { CelebrityChangelogComponent } from '../celebrity/celebrity-changelog/celebrity-changelog.component';
import { BackendService } from 'src/app/services/backend.service';
import { FakeSearch } from 'src/app/mock/fake-search';
import { MdbIconComponent } from 'angular-bootstrap-md';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchComponent, SearchResultComponent, CelebrityComponent, NewsfeedComponent,
      CelebrityChangelogComponent, MdbIconComponent ],
      imports: [ MaterialModule, HttpClientTestingModule, BrowserAnimationsModule, FormsModule ],
      providers: [ { provide: SearchService, useClass: FakeSearch }, BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should try searching', () => {
    inject([HttpTestingController, SearchService],
    (httpMock: HttpTestingController, service: SearchService) => {
    const spy = spyOn(component, 'onSearch');
    component.onSearch('Test');
    fixture.detectChanges();
    expect(spy).toHaveBeenCalled();
    expect(component.lastSearch === 'Test').toBeTruthy();
    const req = httpMock.expectOne('http://localhost:8081/api/v1/celebrities/query/byActorIds');
    expect(req.request.method).toEqual('GET');
    // expect(component.)
    // expect(component.searchValues.indexOf({type: 'name', value: 'Test'}) !== -1).toBeTruthy();
    });
  });
  it('should search', () => {
    inject([HttpTestingController, SearchService],
    (httpMock: HttpTestingController, service: SearchService) => {
      // We call the service
      service.queryElastic('test').subscribe(data => {
        expect(data).toBe({ query: { query_string: {query: 'test'}}});
      });
      // We set the expectations for the HttpClient mock
      const req = httpMock.expectOne('http://.../data/contacts');
      expect(req.request.method).toEqual('GET');
      // Then we set the fake data to be returned by the mock
      req.flush({data: {query: { query_string: {query: 'test'}}}});
    });
  });
  afterEach(inject([HttpTestingController], (httpMock: HttpTestingController) => {
    httpMock.verify();
  }));

});
