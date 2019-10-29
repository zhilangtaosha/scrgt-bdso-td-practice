import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { DataOverviewComponent } from './data-overview/data-overview.component';
import { DataYearComponent } from './data-year/data-year.component';
import { DataSalaryComponent } from './data-salary/data-salary.component';
import { DataGenderComponent } from './data-gender/data-gender.component';

import { DataScienceComponent } from './data-science.component';
import { DataGenderPayComponent } from './data-gender-pay/data-gender-pay.component';
import { DataGridComponent } from './data-grid/data-grid.component';
import { BackendService } from '../services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DataScienceComponent', () => {
  let component: DataScienceComponent;
  let fixture: ComponentFixture<DataScienceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataScienceComponent, DataOverviewComponent, DataYearComponent, DataSalaryComponent,
        DataGenderComponent, DataGenderPayComponent, DataGridComponent,
      ],
      imports: [ HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataScienceComponent);
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
