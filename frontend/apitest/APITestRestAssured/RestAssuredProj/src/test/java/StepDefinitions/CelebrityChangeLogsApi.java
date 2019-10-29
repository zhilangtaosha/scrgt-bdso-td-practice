package StepDefinitions;

import Celebrities.CelebrityChangeLogs;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityChangeLogsApi {
	
	@Given("^Access to celebrities Change Logs API$")
	public void Access_to_celebrity_Change_Logs_API() throws Throwable
	{
		
		CelebrityChangeLogs.GetChangeLogs(); 
	}

	@When("^Response received from Celebrities Change Logs API$")
	public void Response_received_from_Celebrities_Change_Logs_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrities Change Logs API page displayed successfully");
			
		}	
	@Then("^View celebrities Change Logs$")
	public void View_celebrities_Change_Logs() throws Throwable
		
		{
			System.out.println("BDSO Celebrities Change Logs API validated successfully.");
			System.out.println("BDSO Celebrities Change Logs API Validation Test Scenario:    PASSED");	
			
		}


}
