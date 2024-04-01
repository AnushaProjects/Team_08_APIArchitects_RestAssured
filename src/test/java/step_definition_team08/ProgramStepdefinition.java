package step_definition_team08;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload_team08.ProgramPayload;
import request_body_raw_team08.ProgramRequestBody;
import request_body_raw_team08.UserRequestBody;
import utilities_team08.CommonValidation;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class ProgramStepdefinition extends ReusableVariables{
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	ProgramRequestBody userReqbody=new ProgramRequestBody();
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	CommonValidation cv=new CommonValidation();
	ProgramPayload pm;
	String userBody;
	Response userResponse;
	Integer programIds;
	
	
	@Given("Admin creates POST program Request  with valid data in request body")
	public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		pm=userReqbody.returnUserPayload("Program");
		userBody=userReqbody.convertJsonToString(pm);
		LoggerLoad.info("Converted ProgramRequestBody for Creating ProgramId to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request with program endpoint")
	public void admin_sends_https_request_with_program_endpoint() {
		System.out.println("Request Sending: "+userBody);
		userResponse= auth_req_post.body(userBody).when().post(baseURL+"/saveprogram");
		userResponse.prettyPrint();
	}

	@Then("Admin receives {int} Created Status with response body in program {string}")
	public void admin_receives_created_status_with_response_body_in_program(Integer created, String Scenario ) {
	   
		LoggerLoad.info("check userId gets generated for Admin role");
		
		cv.headervalidations(userResponse);
		cv.statusValidations(userResponse, created, "");
        cv.schemavalidation(userResponse,"/Program_Json/post_program_json" );
		
		JsonPath programId = userResponse.jsonPath();
		programIds =programId.get("programId");
		System.out.println(programIds);
		if(Scenario.equalsIgnoreCase("Positive")){
			LoggerLoad.info("Program Id Valid Scenarios - "+programIds);
			configreader.writingdata("program_id",programIds.toString());
		}
		else if(Scenario.equalsIgnoreCase("Negative")){
			LoggerLoad.info("Program Id for Negative Scenarios - " +programIds);
			configreader.writingdata("negative_scenerio_program_id",programIds.toString());
			}
	}
	

//	@Given("Admin creates POST batch Request  with valid data in request body")
//	public void admin_creates_post_batch_request_with_valid_data_in_request_body() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@When("Admin sends HTTPS batch Request with endpoint without authorization")
//	public void admin_sends_https_batch_request_with_endpoint_without_authorization() {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("Admin receives {int} Unauthorized")
//	public void admin_receives_unauthorized(Integer int1) {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
//	}
}
