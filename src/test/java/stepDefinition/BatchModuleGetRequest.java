package stepDefinition;

import java.util.Properties;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.*;
import utilities.ReusableVariables;
import validations.BatchValidation;

import java.util.Properties;

public class BatchModuleGetRequest {
	ReusableVariables rv=new ReusableVariables();
	ReusableMethods rm=new ReusableMethods();
	ConfigReader cr =new ConfigReader();
	BatchValidation bv=new BatchValidation();
	Response response;
	Properties prop =cr.readingdata();
	String ReadBearer=prop.getProperty("bearer");
	
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() {
		RestAssured.baseURI= rv.baseURL;
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
        String searchfield="SAMPLE BATCH12";
		rv.httpRequest= RestAssured.given().
		header("Authorization","Bearer "+ReadBearer);
		response=rv.httpRequest.given().queryParam("searchString", searchfield)
				.when().get(rv.baseURL+rv.GetAllBatch);
		
	}

	@Given("Admin creates GET Request with valid Program Id")
	public void admin_creates_get_request_with_valid_program_id() {
		RestAssured.baseURI= rv.baseURL;
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
	}

	@When("Admin sends HTTPS Request with endpoint for ProgramID")
	public void admin_sends_https_request_with_endpoint_for_program_id() {
		String ProgID="16489";
				  response=rv.httpRequest.pathParam("programId", ProgID)
				  .when().get(rv.baseURL+"/batches/program/{programId}");
	}
	
	@When("Admin sends HTTPS Request for GetAll_batch with valid endpoint")
	public void admin_sends_https_request_for_get_all_batch_with_valid_endpoint() {
	    response=rv.httpRequest.when().get(rv.baseURL+rv.GetAllBatch);
	}
	
	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_ok_status_with_response_body(Integer StatusCode) {
	    response.then().statusCode(200);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
//		bv.Get_Request_ScheemaValidation(response);
//		bv.GetBatch_BatchNameScheemaValidation(response);
//		bv.GetBatch_ProgramIDScheemaValidation(response);
//		bv.GetBatch_BatchIDScheemaValidation(response);
		bv.statusValidations(response, StatusCode);
//		bv.headervalidations(response);
		
	}

	@When("Admin sends HTTPS Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() {
		
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
				response=rv.httpRequest.get("xyz");
	}
	
	@When("Admin sends HTTPS Request with invalid searchfieldID and valid endpoint")
	public void admin_sends_https_request_with_invalid_searchfield_id_and_valid_endpoint() {
		String searchfield="aaaaaaaaaaaaaaaaaaaaa";
		rv.httpRequest= RestAssured.given().
		header("Authorization","Bearer "+ReadBearer);
		response=rv.httpRequest.given().queryParam("searchString", searchfield)
				.when().get(rv.baseURL+rv.GetAllBatch);
	}

	@When("Admin sends HTTPS Request for GetAll_batch with invalid endpoint")
	public void admin_sends_https_request_for_get_all_batch_with_invalid_endpoint() {
	   response=rv.httpRequest.when().get(rv.baseURL+"/Invalid");
	}
	
	
	@Then("Admin receives {int} status with error message Not Found.")
	public void admin_receives_status_with_error_message_not_found(Integer int1) {
	    response.then().statusCode(404);
	    String Respobody=response.body().asPrettyString();
	    System.out.println("Invalid details:" + Respobody);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	}

	@Then("Admin receives {int}  Status with error message unauthorized.")
	public void admin_receives_status_with_error_message_unauthorized(Integer int1) {
	    response.then().statusCode(401);
	    String respobody=response.body().asPrettyString();
	    System.out.println("Unautherized access"+respobody);
	}

	@Given("Admin creates GET Request without autherization")
	public void admin_creates_get_request_without_autherization() {
		RestAssured.baseURI= rv.baseURL;
		rv.httpRequest=RestAssured.given().auth().none();
		response=rv.httpRequest.get(rv.GetAllBatch);
	}
	
	@When("Admin sends HTTPS Request with Invalid endpoint for BatchID")
	public void admin_sends_https_request_with_invalid_endpoint_for_batch_id() {
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
		String batchIdnum="8486";
		response=rv.httpRequest
				.given().pathParam("batchId",batchIdnum)
				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchId}");
	}
	
	@When("Admin sends HTTPS Request with endpoint for BatchID")
	public void admin_sends_https_request_with_endpoint_for_batch_id() {
		String batchIdnum="8765";
		response=rv.httpRequest
				.given().pathParam("batchId",batchIdnum)
				.when().get(rv.baseURL+rv.GetByBatchID+"{batchId}");
	}
	
	@When("Admin sends HTTPS Request with invalid endpoint for ProgramID")
	public void admin_sends_https_request_with_invalid_endpoint_for_program_id() {
		String ProgID="16765";
				  response=rv.httpRequest.pathParam("programId", ProgID)
				  .when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{programId}");
	}

	@When("Admin sends HTTPS Request with endpoint for deleted BatchID")
	public void admin_sends_https_request_with_endpoint_for_deleted_batch_id() {
		String batchIdnum="8768";
		response=rv.httpRequest
				.given().pathParam("batchId",batchIdnum)
				.when().get(rv.baseURL+rv.GetByBatchID+"{batchId}");
		System.out.println(rv.baseURL+rv.GetByBatchID+"{batchId}");
	}
	
	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body.")
	public void admin_receives_ok_status_with_batch_status_field_in_the_response_body(Integer int1, String string) {
	   response.then().statusCode(200);
	   String RespoBody=response.body().asPrettyString();
	   System.out.println("Deleted ID Response body is:"+ RespoBody );
	}

	
	@When("Admin sends HTTPS Request with endpoint for deleted ProgramID")
	public void admin_sends_https_request_with_endpoint_for_deleted_program_id() {
		String ProgID="16585";
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+ReadBearer);
				  response=rv.httpRequest.pathParam("programId", ProgID)
				  .when().get(rv.baseURL+ rv.GetProgramID+"{programId}");
	}

	@When("Admin sends HTTPS Request with endpoint for invalid BatchID")
	public void admin_sends_https_request_with_endpoint_for_invalid_batch_id() {
		String batchIdnum="abc";
		response=rv.httpRequest
				.given().pathParam("batchId",batchIdnum)
				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchId}");
	}
	
	@When("Admin sends HTTPS Request with endpoint for Invalid ProgramID")
	public void admin_sends_https_request_with_endpoint_for_invalid_program_id() {
		String ProgID="xyz";
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+ReadBearer);
				  response=rv.httpRequest.pathParam("programId", ProgID)
				  .when().get(rv.baseURL+ rv.GetProgramID+"{programId}");
		   			  }
	
	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer int1) {
	    response.then().statusCode(404);
	    String respo=response.getBody().asPrettyString();
	    System.out.println("Response Body is:"+ respo);
	}
	
	@When("Admin sends HTTPS Request with Invalid endpoint for Valid BatchName")
	public void admin_sends_https_request_with_invalid_endpoint_for_valid_batch_name() {
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
		String BatchName="Api";
		response=rv.httpRequest
				.given().pathParam("batchName",BatchName)
				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchName}");
	}
	
	@When("Admin sends HTTPS Request with endpoint for Valid BatchName")
	public void admin_sends_https_request_with_endpoint_for_valid_batch_name() {
		String BatchName="SAMPLE BATCH12";
		response=rv.httpRequest
				.given().pathParam("batchName",BatchName)
				.when().get(rv.baseURL+ rv.GetBatchName +"{batchName}");
	}
	
	
	@When("Admin sends HTTPS Request with endpoint for InValid BatchName")
	public void admin_sends_https_request_with_endpoint_for_in_valid_batch_name() {
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+ReadBearer);
		String BatchName="12a";
		response=rv.httpRequest
				.given().pathParam("batchName",BatchName)
				.when().get(rv.baseURL+rv.GetBatchName+"{batchName}");
	}
}
