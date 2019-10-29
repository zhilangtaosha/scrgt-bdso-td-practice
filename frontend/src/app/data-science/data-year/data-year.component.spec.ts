import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataYearComponent } from './data-year.component';
import { BackendService } from 'src/app/services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DataYearComponent', () => {
  let component: DataYearComponent;
  let fixture: ComponentFixture<DataYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataYearComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataYearComponent);
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
