import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CelebrityComponent } from '../celebrity/celebrity.component';
import { SearchResultComponent } from './search-result.component';
import { FormsModule } from '@angular/forms';
import { NewsfeedComponent } from '../celebrity/newsfeed/newsfeed.component';
import { CelebrityChangelogComponent } from '../celebrity/celebrity-changelog/celebrity-changelog.component';
import { BackendService } from 'src/app/services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MaterialModule } from 'src/app/material.module';


describe('SearchResultComponent', () => {
  let component: SearchResultComponent;
  let fixture: ComponentFixture<SearchResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchResultComponent, CelebrityComponent, NewsfeedComponent, CelebrityChangelogComponent ],
      imports: [ FormsModule, MaterialModule, HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultComponent);
    component = fixture.componentInstance;
    component.searchResults = {hasData: false, data: [] };
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
