package starter.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

class BDSOBasePage extends PageObject {

  public void visitPage() {
    this.open();
    getDriver().manage().window().fullscreen();
  }

  public void clickOn(WebElement e) {
    WebElementFacade element = element(e);
    new Actions(getDriver()).moveToElement(e).perform();
    element.waitUntilClickable();
    getJavascriptExecutorFacade().executeScript("arguments[0].click()",e);
  }

  public static void safeSleep(final int secs) {
    try {
      Thread.sleep(secs*1000);
    } catch (InterruptedException e){
      e.printStackTrace();
    }
  }
  public void clearBrowserCookies() {
    getDriver().manage().deleteAllCookies();
  }

  public void shutdownWebDriverSession() {
    getDriver().quit();
  }
}
