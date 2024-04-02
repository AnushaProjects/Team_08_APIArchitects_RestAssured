package step_definition_team08;

import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import request_body_raw_team08.ProgramRequestBody;
import utilities_team08.CommonValidation;
import utilities_team08.ConfigReader;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class ProgramModule_Delete {
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	//ProgramRequestBody userReqbody=new ProgramRequestBody();
	//ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	CommonValidation cv=new CommonValidation();
	Response userResponse;
	//String ReadBearer=prop.getProperty("bearer");
	
	@Given("Admin creates Delete Request for Program")
	public void admin_creates_delete_request_for_program() {
		System.out.println();
		RestAssured.baseURI= reuseVariables.baseURL;
		reuseVariables.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+prop.getProperty("bearer"));
		System.out.println("Test Print");
	}

	@When("Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point")
	public void admin_sends_https_request_for_delete_with_valid_program_name_and_valid_end_point() {
	    String ProgName="QAutomationTester";
	    reuseVariables.httpRequest= RestAssured.given().
	    		header("Authorization","Bearer "+prop.getProperty("bearer"));
	    userResponse=reuseVariables.httpRequest.pathParam("programName", ProgName).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgNameEndPoint+"{/programName}");
	}

	@Then("Admin receives {int} Ok status with message for Delete")
	public void admin_receives_ok_status_with_message_for_delete(Integer int1) {
		userResponse.then().statusCode(200);
		String SuccessMessage=userResponse.getBody().asPrettyString();
		System.out.println("Success message is:"+SuccessMessage);
		int statuscode= userResponse.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
		
	}

	@When("Admin sends HTTPS Request for Delete with Invalid Program Name and Valid End Point")
	public void admin_sends_https_request_for_delete_with_invalid_program_name_and_valid_end_point() {
		String ProgName="123";
	    userResponse=reuseVariables.httpRequest.pathParam("programName", ProgName).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgNameEndPoint+"{/programName}");
	}

	@Then("Admin receives {int} Not Found Status for Delete")
	public void admin_receives_not_found_status_for_delete(Integer int1) {
		userResponse.then().statusCode(404);
	    String RespoBody=userResponse.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= userResponse.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	}

	@Then("Admin receives {int} Unauthorized for Delete")
	public void admin_receives_unauthorized_for_delete(Integer int1) {
		userResponse.then().statusCode(401);
	    String respobody=userResponse.body().asPrettyString();
	    System.out.println("Unautherized access"+respobody);
	}

	@When("Admin sends HTTPS Request for Delete with Valid Program_ID and Valid End Point")
	public void admin_sends_https_request_for_delete_with_valid_program_id_and_valid_end_point() {
		String ProgID="17325";
	    userResponse=reuseVariables.httpRequest.pathParam("programId", ProgID).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgNameEndPoint+"{/programId}");
	}

	@When("Admin sends HTTPS Request for Delete with InValid Program_ID and Valid End Point")
	public void admin_sends_https_request_for_delete_with_in_valid_program_id_and_valid_end_point() {
		String ProgID="abcd";
	    userResponse=reuseVariables.httpRequest.pathParam("programId", ProgID).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgIDEndPoint+"{/programId}");
	}

	@Given("Admin create DELETE Request without Autherization for Program")
	public void admin_create_delete_request_without_autherization_for_program() {
		RestAssured.baseURI= reuseVariables.baseURL;
		reuseVariables.httpRequest= RestAssured.given().auth().none();
	}
	
	@When("Admin sends HTTPS Request for Delete with Valid Program Name and Invalid End Point")
	public void admin_sends_https_request_for_delete_with_valid_program_name_and_invalid_end_point() {
		String ProgName="QAutomationTester";
	    userResponse=reuseVariables.httpRequest.pathParam("programName", ProgName).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgNameEndPoint+"{/INVALID}");
	}

	@When("Admin sends HTTPS Request for Delete with Valid Program_ID and Invalid End Point")
	public void admin_sends_https_request_for_delete_with_valid_program_id_and_invalid_end_point() {
		String ProgID="17325";
	    userResponse=reuseVariables.httpRequest.pathParam("programId", ProgID).
	    		when().delete(reuseVariables.baseURL+reuseVariables.DeleteProgNameEndPoint+"{/INVALID}");
	}

}
