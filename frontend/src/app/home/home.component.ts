import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {KeycloakService} from 'keycloak-angular';
import {ROLES} from '../models/models';

@Component({
  selector: 'app-login',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(protected router: Router,
              protected keycloakAngular: KeycloakService) { }


  ngOnInit() {
    try {
      const userDetails = this.keycloakAngular.getKeycloakInstance().realmAccess.roles;
      if ( userDetails.includes(ROLES.DATA)) {
        this.router.navigateByUrl('/data');
      } else if ( userDetails.includes(ROLES.USER) || userDetails.includes(ROLES.SUPERVISOR)) {
        this.router.navigateByUrl('/celebrities');
      }
      console.log('user details: ', this.keycloakAngular.getKeycloakInstance());
    } catch (e) {
      console.log('user role not recognized', e);
      this.keycloakAngular.logout();
    }
  }
}

