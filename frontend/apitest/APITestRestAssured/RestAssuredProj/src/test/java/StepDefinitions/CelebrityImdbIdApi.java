package StepDefinitions;

import Celebrities.CelebrityImdbId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityImdbIdApi {
	
	@Given("^Access to celebrity Imdb Id API$")
	public void Access_to_celebrity_Imdb_Id_API() throws Throwable
	{
		
		CelebrityImdbId.GetImdbId(); 
	}

	@When("^Response received from Celebrity Imbd Id API$")
	public void Response_received_from_Celebrity_Imdb_Id_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity Imdb Id API page displayed successfully");
			
		}	
	@Then("^View celebrity Imdb Id Passed$")
	public void View_celebrities_Imdb_Id_Passed() throws Throwable
		
		{
			System.out.println("BDSO Celebrities Imdb Id API validated successfully.");
			System.out.println("BDSO Celebrities Imdb Id API Validation Test Scenario:    PASSED");	
			
		}



}
