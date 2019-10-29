import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CelebrityChangelogComponent } from './celebrity-changelog.component';
import { MatDialogModule } from '@angular/material';
import { CelebrityChangelogService } from './celebrity-changelog.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { BackendService } from 'src/app/services/backend.service';

describe('CelebrityChangelogComponent', () => {
  let component: CelebrityChangelogComponent;
  let fixture: ComponentFixture<CelebrityChangelogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CelebrityChangelogComponent ],
      imports: [ MatDialogModule, HttpClientTestingModule ],
      providers: [ CelebrityChangelogService, BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CelebrityChangelogComponent);
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
