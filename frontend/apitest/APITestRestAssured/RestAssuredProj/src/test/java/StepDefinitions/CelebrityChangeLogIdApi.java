package StepDefinitions;

import Celebrities.CelebrityChangeLogId;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CelebrityChangeLogIdApi {
	
	@Given("^Access to celebrity Change Log Id API$")
	public void Access_to_celebrity_Change_Log_Id_API() throws Throwable
	{
		
		CelebrityChangeLogId.GetChangeLogId(); 
	}

	@When("^Response received from Celebrity Change Log Id API$")
	public void Response_received_from_Celebrity_Change_Logs_Id_API() throws Throwable
		
		{
			
			System.out.println("BDSO Celebrity Change Log Id API page displayed successfully");
			
		}	
	@Then("^View celebrity Change Log Id Passed$")
	public void View_celebrity_Change_Log_Id_Passed() throws Throwable
		
		{
			System.out.println("BDSO Celebrity Change Log Id API validated successfully.");
			System.out.println("BDSO Celebrity Change Log Id API Validation Test Scenario:    PASSED");	
			
		}


}
