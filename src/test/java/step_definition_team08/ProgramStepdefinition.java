package step_definition_team08;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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
	String programname;
	String get_all_programs;
	String get_all_programs_bby_Id;
	String delete_program_by_name;
	String delete_program_by_id;
	
	//Program Module:
	//Creating Program
	
	@Given("Admin creates POST program Request  with valid data in request body")
	public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		pm=userReqbody.returnUserPayload("Program");
		userBody=userReqbody.convertJsonToString(pm);
		LoggerLoad.info("Converted ProgramRequestBody for Creating ProgramId to JSON Format " +userBody);
	}

	//valid and Invalid endpoint
	@When("Admin sends HTTPS Request and  request Body with {string} endpoint")
	public void admin_sends_https_request_and_request_body_with_endpoint(String endpoint) {
		LoggerLoad.info("Request Sending: "+userBody);
		if(endpoint.equalsIgnoreCase("valid")) {
		userResponse= auth_req_post.body(userBody).when().post(baseURL+create_program);
		}
		else if(endpoint.equalsIgnoreCase("invalid")) {
			userResponse= auth_req_post.body(userBody).when().post(invalid_endpoint);
			}
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
		
		JsonPath programname1=userResponse.jsonPath();
		programname=programname1.get("programName");
		System.out.println(programId);
		if(Scenario.equalsIgnoreCase("Positive")){
			LoggerLoad.info("Program Id Valid Scenarios - "+programId +" and "+programname);
			configreader.writingdata("program_id",programIds.toString());
			configreader.writingdata("program_name",programname);
		}
		else if(Scenario.equalsIgnoreCase("Negative")){
			LoggerLoad.info("Program Id for Negative Scenarios - " +programIds);
			configreader.writingdata("negative_scenerio_program_id",programIds.toString());
			configreader.writingdata("negative_program_name",programname);
			}
		else if(Scenario.equalsIgnoreCase("Positive2")){
			LoggerLoad.info("Program Id for Negative Scenarios - " +programIds);
			configreader.writingdata("program_Id_chaining",programIds.toString());
			configreader.writingdata("program_name_chaining",programname);
			}
	}
	
	@Then("Admin receives {int} Bad Request Status with message and boolean success programdetails")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_programdetails(Integer statuscode) {
		LoggerLoad.info("check ProgramId gets generated for Admin role");
		cv.headervalidations(userResponse);
		cv.statusValidations(userResponse, statuscode, "Status validation");
        cv.messageValidations(userResponse, false);
        LoggerLoad.info("UserId is not created");
	}

	@When("Admin sends HTTPS batch Request with endpoint without authorization")
	public void admin_sends_https_batch_request_with_endpoint_without_authorization() {
		LoggerLoad.info("Sending the Request UnAuthorised");
		userResponse= noauth_req_post.body(userBody).when().post(baseURL+create_program);
		userResponse.prettyPrint();
	}
	//UnAuthorised
	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer unauthorized) {
		LoggerLoad.info("check programId gets generated ");
		System.out.println(userResponse);
		cv.statusValidations(userResponse, unauthorized, "");
        LoggerLoad.info("ProgramId is not created");
	}
	
	
	    
	//invalid endpoint
	@Then("Admin receives {int} not found  Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer statuscode) {
		LoggerLoad.info("check programId gets generated ");
		System.out.println(userResponse);
		cv.statusValidations(userResponse, statuscode, "Invalid Endpoint");
        LoggerLoad.info("ProgramId is not created");
	   
	}
	
	//no Request body
	@When("Admin sends HTTPS Request and  request Body with no bodyrequest")
	public void admin_sends_https_request_and_request_body_with_no_bodyrequest() {
		LoggerLoad.info("Sending the endpoint with no Request body");
		userResponse= auth_req_post.body("").when().post(baseURL+create_program);
		userResponse.prettyPrint();
	}
	
	//InvalidMethod
	@When("Admin sends HTTPS Request and  request Body with wrongMethod")
	public void admin_sends_https_request_and_request_body_with_wrong_method() {
		LoggerLoad.info("Sending the endpoint with no Request body");
		userResponse= auth_req_post.body("userBody").when().get(baseURL+create_program);
		userResponse.prettyPrint(); 
	}
	
	
	//Invalid Values
	
	@Given("Check if Admin able to create a program with invalid request body {string}")
	public void check_if_admin_able_to_create_a_program_with_invalid_request_body(String InvalidValues) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST request with all mandatory fields and additional");
	   
			LoggerLoad.info("Create userId with Invalid values");
			
			userBody = userReqbody.negativeUserScenario(InvalidValues);
			
			LoggerLoad.info("Request Generated for Negative scenarion:");
			LoggerLoad.info(userBody);
			
			LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
		}
	
	//Update
	
	@Given("Check if Admin able to {string} a program with valid programID endpoint  and valid request body")
	public void check_if_admin_able_to_a_program_with_valid_program_id_endpoint_and_valid_request_body(String updateby) throws InvalidFormatException, IOException {
		pm=userReqbody.returnUserPayloadput("Program");
		List<Map<String, String>> hm=read.getData(path,"Program");
		switch(updateby) {
		case "ProgramName":
			pm.setProgramName(prop.getProperty("program_name"));
			LoggerLoad.info("Updating the Program by Name");
			
			break;
		
		case "ProgramId":
			LoggerLoad.info("Updating the Program by Id");
			pm.setProgramName(prop.getProperty("program_name"));
			pm.setProgramDescription(hm.get(0).get("updateProgramDescription2"));
			
			break;
		}
		userBody=userReqbody.convertJsonToString(pm);
		LoggerLoad.info("Converted ProgramRequestBody for Updating ProgramId to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request and  request Body with {string} endpoint for PUT {string}")
	public void admin_sends_https_request_and_request_body_with_endpoint_for_put(String endpoint, String update) {
		LoggerLoad.info("Request Sending: "+userBody);
		if(endpoint.equalsIgnoreCase("valid")) {
			if(update.equalsIgnoreCase("ProgramName")) {
				userResponse= auth_req_post.body(userBody).when().put(baseURL+update_program_by_name);
			}
			if(update.equalsIgnoreCase("ProgramId")) {
				userResponse= auth_req_post.body(userBody).when().put(baseURL+update_program_by_id);
			}
		
		}
		else if(endpoint.equalsIgnoreCase("invalid")) {
			userResponse= auth_req_post.body(userBody).when().put(invalid_endpoint);
			}
		userResponse.prettyPrint();
	}

	@Then("Admin receives {int} OK Status with updated value in response body.")
	public void admin_receives_ok_status_with_updated_value_in_response_body(Integer statuscode) {
LoggerLoad.info("check userId gets generated for Admin role");
		
		cv.headervalidations(userResponse);
		cv.statusValidations(userResponse, statuscode, "");
        cv.schemavalidation(userResponse,"/Program_Json/post_program_json" );
	}
	
	
	//PUT invalid Endpoint
	
	@Given("Admin creates PUT program Request  with valid data in request body")
	public void admin_creates_put_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		pm=userReqbody.returnUserPayload("Program");
		userBody=userReqbody.convertJsonToString(pm);
		LoggerLoad.info("Converted ProgramRequestBody for Creating ProgramId to JSON Format " +userBody);
	}
	
	@When("Admin sends HTTPS Request and  request Body with {string} endpoint put")
	public void admin_sends_https_request_and_request_body_with_endpoint_put(String string) {
		userResponse= auth_req_post.body(userBody).when().put(invalid_endpoint);
	}
	
	//no bodyREquest
	@When("Admin sends HTTPS Request and  request Body with no bodyrequest put")
	public void admin_sends_https_request_and_request_body_with_no_bodyrequest_put() {
		LoggerLoad.info("Sending the endpoint with no Request body");
		userResponse= auth_req_post.body("").when().put(baseURL+update_program_by_id);
		userResponse.prettyPrint();
	}
	
	//Update with Invalid Data
	
	@Given("Check if Admin able to update a program with invalid request body {string}")
	public void check_if_admin_able_to_update_a_program_with_invalid_request_body(String InvalidValues) throws InvalidFormatException, IOException {
		LoggerLoad.info("Create userId with Invalid values");
		
		userBody = userReqbody.negativeUserScenario(InvalidValues);
		
		LoggerLoad.info("Request Generated for Negative scenarion:");
		LoggerLoad.info(userBody);
		
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
	}


@When("Admin sends HTTPS Request and  request Body with {string} endpoint InvalidPUT")
public void admin_sends_https_request_and_request_body_with_endpoint_invalid_put(String InvalidValues) throws InvalidFormatException, IOException {
	
	LoggerLoad.info("Updating the endpoint with Request body with Invalid values");
	userResponse= auth_req_post.body(userBody).when().put(baseURL+negative_update_program_by_id);
	userResponse.prettyPrint();
}

@Given("Admin creates GET Request with valid Endpoint get")
public void admin_creates_get_request_with_valid_endpoint_get() {
	LoggerLoad.info("Setting the Request to Display all the Programs");
	get_all_programs=baseURL+get_programs;
//	LoggerLoad.info("The given request" +get_all_programs);
	LoggerLoad.info("The given request" +get_all_programs);
}

@When("Admin sends  HTTPS Request with {string} endpoint")
public void admin_sends_https_request_with_endpoint(String endpoint) {
	if(endpoint.equalsIgnoreCase("valid")) {
	userResponse=auth_req_post.when().get(get_all_programs);
	}
	else if(endpoint.equalsIgnoreCase("invalid")) {
		userResponse=auth_req_post.when().get(invalid_endpoint);
	}
	LoggerLoad.info("All Programs gor displayed " +userResponse);
	userResponse.prettyPrint();
	
}
    
@Then("Admin  receives {int} OK Status with response body")
public void admin_receives_ok_status_with_response_body(Integer statuscode) 
{
	cv.headervalidations(userResponse);
	cv.statusValidations(userResponse, statuscode, "");
	LoggerLoad.info("All Programs gor displayed " +userResponse);
	userResponse.prettyPrint();
}

@When("Admin sends HTTPS get Request with endpoint without authorization")
public void admin_sends_https_get_request_with_endpoint_without_authorization() {
    LoggerLoad.info("No authorization");
    userResponse=noauth_req_post.when().get(get_all_programs);
}

@Given("Admin creates GET Request with valid Endpoint")
public void admin_creates_get_request_with_valid_endpoint() {
	LoggerLoad.info("Setting the Request to Display all the Programs by programId");
	get_all_programs_bby_Id=baseURL+get_programs_byid;
	LoggerLoad.info("The given request" +get_all_programs_bby_Id);
}

@When("Admin sends HTTPS Request with a specific programid {string} endpoint")
public void admin_sends_https_request_with_a_specific_programid_endpoint(String endpoint) {
	LoggerLoad.info("All Programs By Id");
	if(endpoint.equalsIgnoreCase("valid")) {
    userResponse=auth_req_post.when().get(get_all_programs_bby_Id);
	}
	else if(endpoint.equalsIgnoreCase("invalid")) {
		 userResponse=auth_req_post.when().get(invalid_endpoint);	
	}
}

//Delete

@Given("Admin creates Delete Request for Program")
public void admin_creates_delete_request_for_program() {
	LoggerLoad.info("Deleting the Program by Name");
	delete_program_by_name=baseURL+delete_by_program_name;
	LoggerLoad.info("The given request" +delete_program_by_name);
}

@When("Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point")
public void admin_sends_https_request_for_delete_with_valid_program_name_and_valid_end_point() {
	System.out.println(prop.getProperty("program_name"));
	userResponse=auth_req_post.when().delete(delete_program_by_name);
	userResponse.prettyPrint();
}

@Then("Admin receives {int} Ok status with message for Delete")
public void admin_receives_ok_status_with_message_for_delete(Integer statuscode) {

	cv.statusValidations(userResponse, statuscode, "Deleted");
	LoggerLoad.info("Deleted : " +userResponse);

}
@When("Admin sends HTTPS Request for Delete with Valid Program Name and Invalid End Point")
public void admin_sends_https_request_for_delete_with_valid_program_name_and_invalid_end_point() {
	System.out.println(prop.getProperty("program_name"));
	String delete_program_by_name1=baseURL+"/deletebyprogname/"+invalid_Id;
	userResponse=auth_req_post.when().delete(delete_program_by_name1);
	userResponse.prettyPrint();
}

@When("Admin sends HTTPS Request for Delete with Valid Program Name and Valid End Point no auth")
public void admin_sends_https_request_for_delete_with_valid_program_name_and_valid_end_point_no_auth() {
	userResponse=noauth_req_post.when().delete(delete_program_by_name);
	userResponse.prettyPrint();
}

@Given("Admin creates Delete Request for Program by Id")
public void admin_creates_delete_request_for_program_by_id() {
	LoggerLoad.info("Deleting the Program by Name");
	delete_program_by_id=baseURL+delete_by_program_id;
	LoggerLoad.info("The given request" +delete_program_by_id);
}
@When("Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point")
public void admin_sends_https_request_for_delete_with_valid_program_id_and_valid_end_point() {
	System.out.println(prop.getProperty("program_id"));
	userResponse=auth_req_post.when().delete(delete_program_by_id);
	userResponse.prettyPrint();
}

@When("Admin sends HTTPS Request for Delete with Valid Program Id and Invalid End Point")
public void admin_sends_https_request_for_delete_with_valid_program_id_and_invalid_end_point() {
	System.out.println(prop.getProperty("program_id"));
	String delete_program_by_id1=baseURL+"/deletebyprogid/"+invalid_integer_Id;
	userResponse=auth_req_post.when().delete(delete_program_by_id1);
	userResponse.prettyPrint();
}

@When("Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point no auth")
public void admin_sends_https_request_for_delete_with_valid_program_id_and_valid_end_point_no_auth() {
	userResponse=noauth_req_post.when().delete(delete_program_by_id);
	userResponse.prettyPrint();
}
	
}
