import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataGenderPayComponent } from './data-gender-pay.component';
import { BackendService } from 'src/app/services/backend.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('DataGenderPayComponent', () => {
  let component: DataGenderPayComponent;
  let fixture: ComponentFixture<DataGenderPayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataGenderPayComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataGenderPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should populate chart and labels when data is loaded', () => {
    let salaryData = [
      { gender: 'Male', salary: 300, year: 2000 },
      { gender: 'Female', salary: 100, year: 2000 },
      { gender: 'Male', salary: 300, year: 2001 },
      { gender: 'Female', salary: 100, year: 2001 },
      { gender: 'Male', salary: 300, year: 2002 },
      { gender: 'Female', salary: 100, year: 2002 },
      { gender: 'Male', salary: 300, year: 2003 },
      { gender: 'Female', salary: 100, year: 2003 },
      { gender: 'Male', salary: 300, year: 2004 },
      { gender: 'Female', salary: 100, year: 2004 },
      { gender: 'Male', salary: 300, year: 2005 },
      { gender: 'Female', salary: 100, year: 2005 },
      { gender: 'Male', salary: 300, year: 2006 },
      { gender: 'Female', salary: 100, year: 2006 },
      { gender: 'Male', salary: 300, year: 2007 },
      { gender: 'Female', salary: 100, year: 2007 },
      { gender: 'Male', salary: 300, year: 2008 },
      { gender: 'Female', salary: 100, year: 2008 },
      { gender: 'Male', salary: 300, year: 2009 },
      { gender: 'Female', salary: 100, year: 2009 },
    ];
    component.SALARY = salaryData;
    component.createChart(salaryData);
    const element: HTMLElement = fixture.nativeElement;
    const chart = element.querySelector('#chart-4 svg');
    expect(chart.getAttribute('width')).toEqual('800');
    expect(chart.getAttribute('height')).toEqual('500');
    const chartLines = element.querySelectorAll('path.line');
    expect(chartLines.length).toEqual(2); // 2 lines in chart
    const legendItems = element.querySelectorAll('.dc-legend-item text');
    expect(legendItems[0].textContent).toEqual('Female Celebrities');
    expect(legendItems[1].textContent).toEqual('Male Celebrities');
    const xAxis = element.querySelector('.x-axis-label');
    expect(xAxis.textContent).toEqual('Year');
    const yAxis = element.querySelector('.y-axis-label');
    expect(yAxis.textContent).toEqual('Salary (in Millions)');
  });

  it('should be empty when no data is loaded', () => {
    const element: HTMLElement = fixture.nativeElement;
    const chart = element.querySelector('#chart-4');
    expect(chart.innerHTML).toEqual('');
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
