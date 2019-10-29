import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MaterialModule } from '../../../material.module';

import { NewsfeedComponent } from './newsfeed.component';
import { NewsFeedService } from './newsfeed.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NewsfeedComponent', () => {
  let component: NewsfeedComponent;
  let fixture: ComponentFixture<NewsfeedComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsfeedComponent ],
      imports: [ MaterialModule, HttpClientTestingModule ],
      providers: [ NewsFeedService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsfeedComponent);
    component = fixture.componentInstance;
    component.newsSource = [{source: 'New York Times'}];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
