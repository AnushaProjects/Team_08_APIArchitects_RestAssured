package step_definition_team08;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import request_body_raw_team08.LoginRequestBody;
import utilities_team08.CommonValidation;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class UserLoginStepDefinition extends ReusableVariables{

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	LoginRequestBody loginReqbody=new  LoginRequestBody();
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	CommonValidation cv=new CommonValidation();
	String reqBody;
	Response loginResponse;
	String bearerToken;
	String userlogout;
	
	@Given("Admin creates request with {string} credentials")
	public void admin_creates_request_with_credentials(String condition) throws IOException, InvalidFormatException {
		List<Map<String, String>> hm=read.getData(path,"Login");
		reqBody = loginReqbody.createLoginRequest(hm,condition);
	}

	
	
	@When("Admin Admin calls Post Https method  with {string}")
	public void admin_admin_calls_post_https_method_with(String endpoint) {
		//System.out.println("Inside Response");
	    if(endpoint.equalsIgnoreCase("valid endpoint")) {
	    	LoggerLoad.info("Passing Valid End Point - "+baseURL+"/login");
	    	loginResponse= noauth_req_post.body(reqBody).when().post(baseURL+"/login");
	    }
	    
	    if(endpoint.equalsIgnoreCase("Invalid endpoint")){
	    	LoggerLoad.info("Passing InValid End Point - "+baseURL+"/log");
	    	loginResponse= noauth_req_post.body(reqBody).when().post(invalid_endpoint);
	    }
	}
	

	@Then("Admin Admin receives {int} created with auto generated token")
	public void admin_admin_receives_created_with_auto_generated_token(Integer successcode) {
		LoggerLoad.info("Capturing Login Status code - "+loginResponse.statusCode());
		
	    cv.headervalidations(loginResponse);
	    cv.statusValidations(loginResponse, successcode, "Checking Status code is Success");
	    cv.schemavalidation(loginResponse,"/UserLogin_json/post_user_login_json" );
	    
	    
		LoggerLoad.info(loginResponse.asString());
	    LoggerLoad.info("Succesfully logged in LMS Application ");	    
	    JsonPath gettoken = loginResponse.jsonPath();
		bearerToken = gettoken.get("token");
		LoggerLoad.info("Retrieving the bearer token from ResponseBody - " +bearerToken);
		configreader.writingdata("bearer",bearerToken);
		LoggerLoad.info("Writing the bearer token to config properties - ");
		LoggerLoad.info("BearerToken - "+bearerToken);
		      
	}

	@Then("Admin Admin receives Admin receives {int} unauthorized")
	public void admin_admin_receives_admin_receives_unauthorized(Integer errorcode) {
		System.out.println(loginResponse.statusCode());
		cv.headervalidations(loginResponse);
	    cv.statusValidations(loginResponse, errorcode, "Checking the error code");
        LoggerLoad.info("UnAuthorized - "+loginResponse.statusCode());
	}
	
	@Then("Admin Admin receives Admin receives {int} BadRequest")
	public void admin_admin_receives_admin_receives_bad_request(Integer badrequest) {
		System.out.println(loginResponse.statusCode());
		cv.headervalidations(loginResponse);
		cv.messageValidations(loginResponse, false);
	    cv.statusValidations(loginResponse, badrequest, "Checking the error code");
        LoggerLoad.info("BADRequest - "+loginResponse.statusCode());
	}
	
	@Given("Admin creates request with valid endpoint for logout")
	public void admin_creates_request_with_valid_endpoint_for_logout() {
	   userlogout=baseURL+logout;
	}

	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_get_https_method_with_valid_endpoint() {
		//String delete_program_by_id1=baseURL+"/deletebyprogid/"+invalid_integer_Id;
		loginResponse=auth_req_post.when().get(userlogout);
		loginResponse.prettyPrint();
	}

	@Then("Admin receives {int} ok  and response with {string}")
	public void admin_receives_ok_and_response_with(Integer statuscode, String statuscode1) {
	    cv.statusValidations(loginResponse, statuscode, statuscode1);
	}
	

	@When("Admin calls Get Https method withinvalid endpoint")
	public void admin_calls_get_https_method_withinvalid_endpoint() {
		
		loginResponse=auth_req_post.when().get(invalid_endpoint);
		loginResponse.prettyPrint();
	}

	@Then("Admin receives {int} Not found")
	public void admin_receives_not_found(Integer status) {
	    cv.statusValidations(loginResponse, status, "invalid EndPoint");
	}

	@When("Admin calls Get Https method with valid endpoint no auth")
	public void admin_calls_get_https_method_with_valid_endpoint_no_auth() {
		loginResponse=noauth_req_post.when().get(invalid_endpoint);
		loginResponse.prettyPrint();
	}


	@Then("Admin receives {int}  unauthorized")
	public void admin_receives_unauthorized(Integer status) {
	    cv.statusValidations(loginResponse, status, "UnAuthorised");
	}
}
