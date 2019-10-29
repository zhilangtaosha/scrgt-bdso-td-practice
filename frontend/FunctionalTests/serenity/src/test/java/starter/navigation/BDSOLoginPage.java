package starter.navigation;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;

import org.openqa.selenium.WebElement;

@DefaultUrl("/login")
public class BDSOLoginPage extends BDSOBasePage {

  @FindBy(id="username")
  private WebElementFacade usernameTextField;

  @FindBy(id="password")
  private WebElementFacade passwordTextField;


  @FindBy(id="kc-login")
  private WebElementFacade loginBtn;

  @FindBy(xpath="/html/body/app-root/div/app-header/div/mat-toolbar/button")
  private WebElementFacade logoutBtn;

  public void enterUserCredentials(String username_str, String password_str) {
    usernameTextField.type(username_str);
    passwordTextField.type(password_str);
    loginBtn.click();
  }

  public void logoutSession() {
    logoutBtn.click();
  }
}
