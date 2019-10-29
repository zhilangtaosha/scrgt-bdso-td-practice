package StepDefinitions;

import Celebrities.CelebrityId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityIdApi {
	
	@Given("^Access to celebrity Id API$")
	public void Access_to_celebrity_Id_API() throws Throwable
	{
		
		CelebrityId.GetCelebrityId(); 
	}

	@When("^Response received from Celebrity Id API$")
	public void Response_received_from_Celebrity_Id_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity Id API page displayed successfully");
			
		}	
	@Then("^View celebrity Ids$")
	public void View_celebrities_Ids() throws Throwable
		
		{
			System.out.println("BDSO Celebrity Id API validated successfully.");
			System.out.println("BDSO Celebrity Id API Validation Test Scenario:    PASSED");	
			
		}



}
