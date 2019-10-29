import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataOverviewComponent } from './data-overview.component';
import { BackendService } from 'src/app/services/backend.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { summaryCount } from '../../mock/api/backend';
import { DecimalPipe, CurrencyPipe } from '@angular/common';

describe('DataOverviewComponent', () => {
  let component: DataOverviewComponent;
  let fixture: ComponentFixture<DataOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataOverviewComponent ],
      imports: [ HttpClientTestingModule ],
      providers: [ BackendService, DecimalPipe, CurrencyPipe ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should init', () => {
    // const spy = spyOn(component, 'ngOnInit').and.callThrough();
    // // fixture.detectChanges();
    // expect(spy).toHaveBeenCalled();
    // expect(component.filmsTotal).toBeTruthy();
    const numPipe = new DecimalPipe('en-US');
    const dollarPipe =  new CurrencyPipe('en-US');
    component.filmsTotal = summaryCount;
    fixture.detectChanges();
    const element: HTMLElement = fixture.nativeElement;
    const num = element.querySelector('#movieCount');
    expect(num.innerHTML.valueOf()).toEqual(' ' + numPipe.transform(summaryCount.movieCount) + ' ');
    const num2 = element.querySelector('#multiActor');
    expect(num2.innerHTML.valueOf()).toEqual(' ' + numPipe.transform(summaryCount.multipleActorCount) + ' ');
    const num3 = element.querySelector('#totalBox');
    expect(num3.innerHTML).toEqual(' ' + dollarPipe.transform(summaryCount.totalBoxOffice) + ' ');
  });
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
