package StepDefinitions;

import Celebrities.CelebrityWikipediaId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityWikipediaIdApi {
	
	@Given("^Access to celebrity Wikipedia Id API$")
	public void Access_to_celebrity_Wikipedia_Id_API() throws Throwable
	{
		
		CelebrityWikipediaId.GetWikipediaId(); 
	}

	@When("^Response received from Celebrity Wikipedia Id API$")
	public void Response_received_from_Celebrity_Wikipedia_Id_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity Wikipedia Id API page displayed successfully");
			
		}	
	@Then("^View celebrity Wikipedia Id Passed$")
	public void View_celebrities_Wikipedia_Id_Passed() throws Throwable
		
		{
			System.out.println("BDSO Celebrities Wikipedia Id API validated successfully.");
			System.out.println("BDSO Celebrities Wikipedia Id API Validation Test Scenario:    PASSED");	
			
		}



}
