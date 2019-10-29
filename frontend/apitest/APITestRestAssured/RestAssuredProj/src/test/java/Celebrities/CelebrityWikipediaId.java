package Celebrities;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CelebrityWikipediaId {
	int id;

	@Test
	 public static void GetWikipediaId()
	 {

		 RestAssured.baseURI = System.getProperty("webdriver.base.url");
		 RestAssured.useRelaxedHTTPSValidation();

	 	 RequestSpecification httpRequest = RestAssured.given()
			 .pathParam("id", "1 - 5");

	 Response response = httpRequest.request(Method.GET, "/api/v1/celebrities/elastic/actor/wikipedia/{id}");
	// ResponseBody body = response.getBody();
	 int statusCode = response.getStatusCode();
	 Assert.assertEquals(statusCode, 200);

	 String responseBody = response.getBody().asString();
	 System.out.println("Status Code Returned is =>  " + statusCode);
	 System.out.println("Response Body is =>  " + responseBody);
	// System.out.println("Response Body is =>  " + body.asString());

	 }

}
