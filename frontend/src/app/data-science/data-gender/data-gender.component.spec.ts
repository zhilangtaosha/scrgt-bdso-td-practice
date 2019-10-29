import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';

import { DataGenderComponent } from './data-gender.component';
import { BackendService } from 'src/app/services/backend.service';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FilmsByGender } from '../../models/models';

describe('DataGenderComponent', () => {
  let component: DataGenderComponent;
  let fixture: ComponentFixture<DataGenderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataGenderComponent ],
      imports: [ HttpClientTestingModule],
      providers: [ BackendService ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataGenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should populate chart and labels when data is loaded', () => {
    let filmsData = [
      { gender: 'Male', movieCount: 300, year: 2000 },
      { gender: 'Female', movieCount: 100, year: 2000 },
      { gender: 'Male', movieCount: 300, year: 2001 },
      { gender: 'Female', movieCount: 100, year: 2001 },
      { gender: 'Male', movieCount: 300, year: 2002 },
      { gender: 'Female', movieCount: 100, year: 2002 },
      { gender: 'Male', movieCount: 300, year: 2003 },
      { gender: 'Female', movieCount: 100, year: 2003 },
      { gender: 'Male', movieCount: 300, year: 2004 },
      { gender: 'Female', movieCount: 100, year: 2004 },
      { gender: 'Male', movieCount: 300, year: 2005 },
      { gender: 'Female', movieCount: 100, year: 2005 },
      { gender: 'Male', movieCount: 300, year: 2006 },
      { gender: 'Female', movieCount: 100, year: 2006 },
      { gender: 'Male', movieCount: 300, year: 2007 },
      { gender: 'Female', movieCount: 100, year: 2007 },
      { gender: 'Male', movieCount: 300, year: 2008 },
      { gender: 'Female', movieCount: 100, year: 2008 },
      { gender: 'Male', movieCount: 300, year: 2009 },
      { gender: 'Female', movieCount: 100, year: 2009 },
    ];
    component.FILMS = filmsData;
    component.createChart(filmsData);
    const element: HTMLElement = fixture.nativeElement;
    const chart = element.querySelector('#chart-3 svg');
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
    expect(yAxis.textContent).toEqual('Films (in Thousands)');
  });

  it('should be empty when no data is loaded', () => {
    const element: HTMLElement = fixture.nativeElement;
    const chart = element.querySelector('#chart-3');
    expect(chart.innerHTML).toEqual('');
  });

  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
