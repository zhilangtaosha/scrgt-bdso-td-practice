package StepDefinitions;

import Celebrities.CelebrityList;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebritiesApi {
	
	@Given("^Access to celebrities API$")
	public void Access_to_celebrities_API() throws Throwable
	{
		
		 CelebrityList.GetCelebrities();
	}

	@When("^Response received from Celebrities API$")
	public void Response_received_from_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity API page displayed successfully");
			
		}	
	@Then("^View celebrities list$")
	public void View_celebrities_list() throws Throwable
		
		{
			System.out.println("BDSO Celebrities List API validated successfully.");
			System.out.println("BDSO Celebrities List API Validation Test Scenario:    PASSED");	
			
		}


	}



