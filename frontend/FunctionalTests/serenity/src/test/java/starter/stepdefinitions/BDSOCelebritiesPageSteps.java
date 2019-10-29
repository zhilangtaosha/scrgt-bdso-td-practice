package starter.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Condition;
import org.junit.Assume;
import starter.navigation.BDSOCelebritiesPage;

import static org.assertj.core.api.Assertions.assertThat;
import static starter.matchers.TextMatcher.textOf;

public class BDSOCelebritiesPageSteps {


    BDSOCelebritiesPage bdsoCelebritiesPage;


    @Given("^(?:.*) is on the BDSO Celebrities page")
    public void i_am_on_the_BDSO_Celebrities_page() {
        bdsoCelebritiesPage.visitPage();
    }

    @When("Entered a search string \"([^\"]*)\"$")
    public void enterSearchCriteria(String searchstring) {
        bdsoCelebritiesPage.enterSearchString(searchstring);
    }

    @Then("Search results shown")
    public void showSearchResults() {
      bdsoCelebritiesPage.visitPage();
    }

    @When("celebrity \"([^\"]*)\" is selected")
    public void selectACelebrity(String celeb) {
        bdsoCelebritiesPage.visitPage();
        bdsoCelebritiesPage.selectCelebrity(celeb);
    }

    @Then("celebrity details is displayed for \"([^\"]*)\"$")
    public void showcelebrityDetails(String celeb) {
        bdsoCelebritiesPage.showCelebrityDetails(celeb);
    }


}
