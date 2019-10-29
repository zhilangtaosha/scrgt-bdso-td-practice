import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CelebrityComponent } from './celebrity.component';
import { NewsfeedComponent } from './newsfeed/newsfeed.component';
import { FormsModule } from '@angular/forms';
import { CelebrityChangelogComponent } from './celebrity-changelog/celebrity-changelog.component';
import { AppPageComponent } from 'src/app/common/app-page/app-page.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BackendService } from 'src/app/services/backend.service';
import { celebrityData } from '../../mock/api/backend';
import { MaterialModule } from 'src/app/material.module';
import { CelebrityChangelogService } from './celebrity-changelog/celebrity-changelog.service';
import { ChangeDetectorRef } from '@angular/core';

describe('CelebrityComponent', () => {
  let component: CelebrityComponent;
  let fixture: ComponentFixture<CelebrityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CelebrityComponent, NewsfeedComponent, CelebrityChangelogComponent, AppPageComponent, ],
      imports: [ FormsModule, InfiniteScrollModule, MaterialModule, HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CelebrityComponent);
    component = fixture.componentInstance;
    component.celebDetails = celebrityData;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should allow editing', () => {
    // const spy = spyOn(component, 'editData');
    component.editData();
    expect(component.editing).toBeTruthy();
    expect(component.showSave).toBeTruthy();
    expect(component.canEdit).toBeFalsy();
  });
  it('should allow saving', () => {
    component.saveData();
    expect(component.editing).toBeFalsy();
    expect(component.showSave).toBeFalsy();
    expect(component.canEdit).toBeTruthy();
  });
  it('should allow reccomendation clicking', () => {
    const spy = spyOn(component, 'onRecommendationClick');
    component.onRecommendationClick(1, 'Nicolas Cage');
    expect(spy).toHaveBeenCalled();
  });
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
