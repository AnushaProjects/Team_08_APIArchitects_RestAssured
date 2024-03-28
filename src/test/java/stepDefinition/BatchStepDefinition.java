package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import RequestBodyRaw.LoginRequestBody;
import RequestBodyRaw.ProgramRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.ConfigReader;
import utilities.ExcelReader1;
import utilities.ReusableMethods;
import utilities.ReusableVariables;


public class BatchStepDefinition extends ReusableVariables{
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	ProgramRequestBody programreqBody=new ProgramRequestBody ();
	ExcelReader1 read = new ExcelReader1();
	String programrequestBody;
	Response ProgramResponse;
	ConfigReader configreader=new ConfigReader();
    Properties prop =configreader.readingdata();

	
	@Given("Admin creates POST program Request  with valid data in request body")
	public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		 List<Map<String, String>> hm=read.getData(path,"Program");
		 programrequestBody = programreqBody.createProgramRequest(hm);	}

	@When("Admin sends HTTPS Request with program endpoint")
	public void admin_sends_https_request_with_program_endpoint() {
		System.out.println("Inside post program Response");
		System.out.println( "Bearer " + prop.getProperty("bearer"));
		System.out.println(programrequestBody);
		//ProgramResponse= given().header("Content-Type","application/json").body(programrequestBody).when().post(baseURL+"/saveprogram");
ProgramResponse= given().header("Content-Type","application/json").header("Authorization","Bearer " + prop.getProperty("bearer"))
.body(programrequestBody).when().post(baseURL+"/saveprogram");

ProgramResponse.prettyPrint();
	}
	
	@Given("Admin creates POST Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() {
	   
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
	    	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
	    	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer int1) {
	    
	}

	@Given("Admin creates POST Request  with existing value in request body")
	public void admin_creates_post_request_with_existing_value_in_request_body() {
	    	}

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer int1) {
	    	}

	@Given("Admin creates POST Request  with invalid data in request body")
	public void admin_creates_post_request_with_invalid_data_in_request_body() {
	   	}

	@Given("Admin creates POST Request")
	public void admin_creates_post_request() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("Admin sends HTTPS Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("Admin receives {int} not found  Status")
	public void admin_receives_not_found_status(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Given("Admin creates POST Request with missing additional fields")
	public void admin_creates_post_request_with_missing_additional_fields() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	

	@Given("Admin creates POST Request with inactive program id")
	public void admin_creates_post_request_with_inactive_program_id() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}
