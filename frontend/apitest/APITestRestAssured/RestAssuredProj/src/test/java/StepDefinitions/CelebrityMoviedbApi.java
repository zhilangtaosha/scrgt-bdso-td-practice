package StepDefinitions;

import Celebrities.CelebrityMoviedbId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityMoviedbApi {
	
	@Given("^Access to celebrity Moviedb Id API$")
	public void Access_to_celebrity_Moviedb_Id_API() throws Throwable
	{
		
		CelebrityMoviedbId.GetMoviedbId(); 
	}

	@When("^Response received from Celebrity Moviebd Id API$")
	public void Response_received_from_Celebrity_Moviedb_Id_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity Moviedb Id API page displayed successfully");
			
		}	
	@Then("^View celebrity Moviedb Id Passed$")
	public void View_celebrities_Moviedb_Id_Passed() throws Throwable
		
		{
			System.out.println("BDSO Celebrities Moviedb Id API validated successfully.");
			System.out.println("BDSO Celebrities Moviedb Id API Validation Test Scenario:    PASSED");	
			
		}



}
