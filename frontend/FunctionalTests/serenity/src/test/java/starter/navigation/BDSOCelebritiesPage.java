package starter.navigation;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.By;
import org.junit.Assert;


public class BDSOCelebritiesPage extends BDSOBasePage {

  @FindBy(id="searchField")
  private WebElementFacade searchTextField;

  @FindBy(id="searchBtn")
  private WebElementFacade searchButton;

  @FindBy(id="cardImg-0-")
  private WebElementFacade card;

  @FindBy(id="mainTitle")
  private WebElementFacade cardMainTitle;

  private String selectedCelebrity;

  public void enterSearchString(String searchstring) {
    safeSleep(20);
    searchTextField.type(searchstring);

    searchButton.click();
  }
  public void confirmResults() {
  }

  public void selectCelebrity(String celeb) {
    safeSleep(20);
    card.click();
  }

  public void showCelebrityDetails(String celeb) {
    safeSleep(10);
    Assert.assertTrue(cardMainTitle.getText().equals(celeb));
  }


}
