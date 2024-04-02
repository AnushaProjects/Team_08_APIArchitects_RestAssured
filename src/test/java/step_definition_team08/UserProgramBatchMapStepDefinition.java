package step_definition_team08;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities_team08.CommonValidation;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableVariables;

public class UserProgramBatchMapStepDefinition extends ReusableVariables {
	
	Response resBody;
	CommonValidation cv=new CommonValidation();
	String get_all_Admins_assigned_ProgramBatches;
	String get_assigned_PB_by_Admin;
	
	@Given("Admin  creates GET Request to retrieve all Admins assigned to programsbatches")
	public void admin_creates_get_request_to_retrieve_all_admins_assigned_to_programs_batches() {
		//.contentType(ContentType.JSON)
		LoggerLoad.info("Admin creates GET Request to retrieve all Admins assigned to programs batches");
		get_all_Admins_assigned_ProgramBatches=baseURL+userPBM;
		
		
		}
	
	@When("Admin  sends HTTPS Request")
	public void admin_sends_https_request1() {
	    
	    resBody=auth_req_post.when().get(get_all_Admins_assigned_ProgramBatches);
	    resBody.prettyPrint();
	}

	@Then("Admin  receives {int} OK")
	public void admin_receives_ok(Integer statusCode) {		
		LoggerLoad.info("The Status :" +resBody.statusCode());
//		cv.statusValidations(resBody, statusCode, "Status");
		cv.headervalidations(resBody);
		LoggerLoad.info("All Admins assigned to programs batches will be displayed");
	}
	
	// Auth
	@When("Admin sends HTTPS Request  with NoAuth")
	public void admin_sends_https_request_with_no_auth() {
		LoggerLoad.info("User creates get request");
		resBody=noauth_req_post.when().get(get_all_Admins_assigned_ProgramBatches);
		resBody.prettyPrint();
	}

	@Then("Admin  receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer statuscode) {
		LoggerLoad.info("The respose ");
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, statuscode, "Error Message");
        LoggerLoad.info("All Admins assigned to programs batches will not be displayed");
	}
	
	
	//userRoleProgramBatchMap/{userId}- valid id
	@Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by {string} AdminID")
	public void admin_creates_get_request_to_retrieve_admin_assigned_to_program_batch_by_admin_id(String userId) {
		
		if(userId.equalsIgnoreCase("invalid")) {
		LoggerLoad.info("Admin creates GET Request to retrieve all Admins assigned to programs batches");
		get_assigned_PB_by_Admin=baseURL+userPBM+invalid_Id;
		}
		else if(userId.equalsIgnoreCase("valid")) {
			LoggerLoad.info("Admin creates GET Request to retrieve all Admins assigned to programs batches with Invalid Admin");
			get_assigned_PB_by_Admin=baseURL+userBPM_by_id;
			}
		
	}

	@When("Admin sends HTTPS Request")
	public void admin_sends_https_request() {
		 resBody=auth_req_post.when().get(get_assigned_PB_by_Admin);
		    resBody.prettyPrint();
	}

	@Then("Admin receives {int} Not Found")
	public void admin_receives_not_found(Integer statuscode) {
		LoggerLoad.info("The respose ");
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, statuscode, "Error Message");
        LoggerLoad.info("All Admins assigned to programs batches will not be displayed");
	}
	
	@When("Admin sends HTTPS Request  with NoAuthAdmin")
	public void admin_sends_https_request_with_no_auth_admin() {
		LoggerLoad.info("User creates get request");
		resBody=noauth_req_post.when().get(get_assigned_PB_by_Admin);
		resBody.prettyPrint();
	}

}
