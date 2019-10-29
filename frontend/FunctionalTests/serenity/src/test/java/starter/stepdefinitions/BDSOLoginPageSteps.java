package starter.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Condition;
import org.junit.Assume;
import starter.navigation.BDSOLoginPage;
import starter.navigation.BDSOCelebritiesPage;

import static org.assertj.core.api.Assertions.assertThat;
import static starter.matchers.TextMatcher.textOf;

public class BDSOLoginPageSteps {

    BDSOLoginPage bdsoLoginPage;
    BDSOCelebritiesPage bdsoCelebritiesPage;

    @Given("^(?:.*) is on the BDSO login page")
    public void i_am_on_the_BDSO_login_page() {
        bdsoLoginPage.visitPage();
    }

    @When("enters \"([^\"]*)\" and \"([^\"]*)\" values")
    public void userEnterCredentials(String username_str, String password_str) {
      bdsoLoginPage.enterUserCredentials(username_str, password_str);
    }

    @Then("Bdso Home Page displayed")
    public void confirmHomePage() {
      bdsoCelebritiesPage.visitPage();
    }

    @Then("user clicks logout")
    public void clickOnLogout() {
      bdsoLoginPage.logoutSession();
    }

}
