import { Injectable } from '@angular/core';

@Injectable()
export class FakeKeycloak {
  instance = { profile: {username: 'Tester'}, realmAccess: {roles: 'Testing'}};
  constructor() {}
  getKeycloakInstance() {
    return this.instance;
  }
  logout() {
    return true;
  }
}
