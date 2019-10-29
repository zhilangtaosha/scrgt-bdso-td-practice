package TestRunner;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features="Features",glue= {"StepDefinitions"}, plugin = { "pretty", "html:target/cucumber-reports" })

	public class Runner {
	
	}

	
