import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MaterialModule } from '../material.module';

import { HeaderComponent } from './header.component';
import {
  MatToolbarModule,
  MatIconModule,
  MatMenuModule
} from '@angular/material';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { FakeKeycloak } from '../mock/fake-keycloak';
import { By } from '@angular/platform-browser';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let service: FakeKeycloak;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HeaderComponent],
      imports: [MatToolbarModule, MatMenuModule, MatIconModule],
      providers: [{ provide: KeycloakService, useClass: FakeKeycloak }]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    service = new FakeKeycloak();
    fixture.detectChanges();
  });
  it('should check header-note', () => {
    let el = fixture.debugElement.query(By.css('.header-note'));
    let spanEl = el.nativeElement;
    expect(spanEl.innerHTML).toContain('BioCeleb');
  });
  it('should check logged-user', () => {
    let el = fixture.debugElement.query(By.css('#cmpy'));
    let spanEl = el.nativeElement;
    expect(spanEl.innerHTML).toContain('GlobalTier Studios');
  });
  it('should check logged-user', () => {
    let el = fixture.debugElement.query(By.css('#welcome'));
    let spanEl = el.nativeElement;
    expect(spanEl.innerHTML).toContain('Welcome,');
  });
  it('should check logged-user', () => {
    let el = fixture.debugElement.query(By.css('.btn-sm'));
    let spanEl = el.nativeElement;
    expect(spanEl.innerHTML).toContain('logout');
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should logout', () => {
    // const foo = require('../mock/fake-keycloak');
    spyOn(service, 'logout');
    expect(service.logout).toBeTruthy();
  });
  afterAll(() => {
    TestBed.resetTestingModule();
  });
});
