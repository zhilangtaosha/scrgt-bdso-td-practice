import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo(route) {
    return browser.get(browser.baseUrl + route) as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root h1')).getText() as Promise<string>;
  }
}
