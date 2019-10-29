import { Component, OnInit } from '@angular/core';
import {KeycloakService} from 'keycloak-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  title = 'CELEBRITY BIOMETRICS';
  user = '';

  constructor(protected keycloakAngular: KeycloakService) { }

  ngOnInit() {
    this.user = this.keycloakAngular.getKeycloakInstance().profile.username;
  }

  onLogout() {
    this.keycloakAngular.logout();
  }
}
