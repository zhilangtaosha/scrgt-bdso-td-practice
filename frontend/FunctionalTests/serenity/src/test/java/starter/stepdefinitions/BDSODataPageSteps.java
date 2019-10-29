package starter.stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Condition;
import org.junit.Assume;
import starter.navigation.BDSODataPage;

import static org.assertj.core.api.Assertions.assertThat;
import static starter.matchers.TextMatcher.textOf;

public class BDSODataPageSteps {


    BDSODataPage bdsoDataPage;

    @Then("display data science page")
    public void i_am_on_the_BDSO_Data_page() {
        bdsoDataPage.visitPage();
    }

}
