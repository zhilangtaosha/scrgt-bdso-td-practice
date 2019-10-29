import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataSalaryComponent } from './data-salary.component';
import { BackendService } from '../../services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DataSalaryComponent', () => {
  let component: DataSalaryComponent;
  let fixture: ComponentFixture<DataSalaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataSalaryComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataSalaryComponent);
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
