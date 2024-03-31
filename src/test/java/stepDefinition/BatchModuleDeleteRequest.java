package stepDefinition;

import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.ConfigReader;
import utilities.LoggerLoad;
import utilities.ReusableVariables;
import validations.BatchValidation;

public class BatchModuleDeleteRequest {
	
	ReusableVariables rv=new ReusableVariables();
	BatchValidation bv=new BatchValidation();
	ConfigReader cr =new ConfigReader();
	Response response;
	Properties prop =cr.readingdata();
	String ReadBearer=prop.getProperty("bearer");
	
	@Given("Admin creates DELETE Request")
	public void admin_creates_delete_request() {
		RestAssured.baseURI= rv.baseURL;
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
	}

	@When("Admin sends HTTPS Request with valid BatchId and endpoint")
	public void admin_sends_https_request_with_valid_batch_id_and_endpoint() {
		String DelID="8768"; //8768 8765
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+ReadBearer);
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_ok_status_with_message(Integer Statuscode) {
		response.then().statusCode(200);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
		bv.statusValidations(response, statuscode);
		//bv.headervalidations(response);
	    
	}

	@When("Admin sends HTTPS Request  with valid BatchId and invalid endpoint")
	public void admin_sends_https_request_with_valid_batch_id_and_invalid_endpoint() {
		String DelID="8765"; //8768
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+ReadBearer);
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.Batch_Invalid_EndPoint+"{id}");
	}

	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer int1) {
		response.then().statusCode(404);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with invalid BatchId valid endpoint")
	public void admin_sends_https_request_with_invalid_batch_id_valid_endpoint() {
		String DelID="12"; //8768
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+ReadBearer);
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Given("Admin creates DELETE Request with unauthorized access")
	public void admin_creates_delete_request_with_unauthorized_access() {
		rv.httpRequest=RestAssured.given();
		
	}
	
	@When("Admin sends HTTPS Request with valid BatchId and endpoint for unautherized")
	public void admin_sends_https_request_with_valid_batch_id_and_endpoint_for_unautherized() {
		String DelID="8768"; //8768 8765
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Then("Admin receives {int} Unauthorized Status")
	public void admin_receives_unauthorized_status(Integer int1) {
		response.then().statusCode(401);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	}
}
