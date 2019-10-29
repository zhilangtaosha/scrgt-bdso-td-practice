import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialogModule } from '@angular/material';

import { HistoryViewComponent } from './history-view.component';

describe('HistoryViewComponent', () => {
  let component: HistoryViewComponent;
  let fixture: ComponentFixture<HistoryViewComponent>;

  const data = {
    changes: {
      oldValue: 'This is a test',
      newValue: 'This is a new test'
    }
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoryViewComponent ],
      imports: [ MatDialogModule ],
      providers: [ {provide: MAT_DIALOG_DATA, useValue: data} ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should add spans around changed text', () => {
    const origString = 'This is a test ';
    const newString = 'This is a <span class="highlight">new</span> test ';
    expect(component.orig).toBe(origString);
    expect(component.update).toBe(newString);
  });
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
