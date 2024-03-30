package step_definition_team08;


import utilities_team08.LoggerLoad;
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
import request_body_raw_team08.UserRequestBody;
import utilities_team08.CommonValidation;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class UserModuleStepDefinition extends ReusableVariables  {
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	UserRequestBody userReqbody=new UserRequestBody();
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	CommonValidation cv=new CommonValidation();
	String userreqBodyAll;
	Response userResponse;
	Response resBody;
	String userIds;
	String get_all_roles;
	String get_all_admins;
	String get_admin_by_userId;
	String get_all_active_admins;
	String get_count_active_inactive;
	String get_count_active_inactive_roleid;
	String get_admin_by_batchId;
	String get_admin_by_programId;
	String get_admin_by_roleId;
	
	
	
	//Creating UserId with Roles
	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
		userreqBodyAll = userReqbody.createUserRequest(hm);
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		System.out.println(userreqBodyAll);
		System.out.println(prop.getProperty("bearer"));
		userResponse= auth_req_post.body(userreqBodyAll).when().post(baseURL+"/users/roleStatus");
		userResponse.prettyPrint();
	}
	
	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer created) {
		
        cv.statusValidations(userResponse,created);
        cv.headervalidations(userResponse);
        cv.schemavalidation(userResponse,"/User_json/post_user_json" );
        
        LoggerLoad.info("UserId Created - "+userResponse.statusCode());
		JsonPath userId = userResponse.jsonPath();
		userIds = userId.get("userId");
		System.out.println("UserId created with MandatoryField - " +userIds);
		configreader.writingdata("user_id_with_madatory_field",userIds);
		
		
	}
	
	
	@Given("Admin creates POST request with all mandatory fields and additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		userreqBodyAll = userReqbody.createUserRequest(hm);
	}
	
	@Then("Admin receives {int} Created Status with response body and Save userId for {string}.")
	public void admin_receives_created_status_with_response_body_and_save_user_id_for(Integer created, String Scenario) {
		System.out.println(userResponse.statusCode());
        Assert.assertEquals(userResponse.statusCode(), created);
        LoggerLoad.info("Success - "+userResponse.statusCode());
		
		JsonPath userId = userResponse.jsonPath();
		userIds = userId.get("userId");
		System.out.println(userIds);
		if(Scenario.equalsIgnoreCase("Positive")){
			System.out.println("UserId with Admin Role for Valid Scenarios - "+userIds  );
			configreader.writingdata("user_id_with_All_field",userIds);
		}
		else if(Scenario.equalsIgnoreCase("Negative")){
			System.out.println("UserId with Admin Role to use it in Negative Scenarios - " +userIds);
			configreader.writingdata("negative_scenerio_user_Id",userIds);
			
			}
	}

	//Check if admin is able to retrieve all the available roles
	
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() {
		LoggerLoad.info("User creates get request");
		get_all_roles=reuseVariables.baseURL+"/users/roles";
		get_all_admins=reuseVariables.baseURL+"/users";
		get_all_active_admins=reuseVariables.baseURL+"/users/activeUsers";
		get_count_active_inactive=reuseVariables.baseURL+"/users/byStatus";
	}

	@When("Admin sends HTTPS Request with GET All Roles endpoint")
	public void admin_sends_https_request_with_get_all_roles_endpoint() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_all_roles);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}

	@Then("Admin receives {int} OK")
	public void admin_receives_ok(Integer successcode) {
		System.out.println(resBody.statusCode());
        Assert.assertEquals(resBody.statusCode(), successcode);
        LoggerLoad.info("Success - "+resBody.statusCode());
		
	}
	
	//GET All Admins
	
	@When("Admin sends HTTPS Request with valid endpoint for AllAdmins")
	public void admin_sends_https_request_with_valid_endpoint_for_all_admins() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_all_admins);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}

	@Then("Admin receives {int} OK Status with response body")
	public void admin_receives_ok_status_with_response_body(Integer successcode) {
		System.out.println(resBody.statusCode());
        Assert.assertEquals(resBody.statusCode(), successcode);
        LoggerLoad.info("Success - "+resBody.statusCode());
	}
	
	//Get Admin with AdminId
	
	@Given("Admin creates GET Request with valid AdminId")
	public void admin_creates_get_request_with_valid_admin_id() {
		LoggerLoad.info("User creates get request");
		get_admin_by_userId=reuseVariables.baseURL+"/users/"+prop.getProperty("user_id_with_All_field");    //user_id_with_All_field
	}
	
	@When("Admin sends HTTPS Request with valid endpoint for AdminByID")
	public void admin_sends_https_request_with_valid_endpoint_for_admin_by_id() {
		System.out.println("Inside When");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_admin_by_userId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
	
	//Get Active Admin
	@When("Admin sends HTTPS Request with valid endpoint for ActiveAdmin")
	public void admin_sends_https_request_with_valid_endpoint_for_active_admin() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_all_active_admins);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
	
	//GET CountOf Active/InActive
	
	@When("Admin sends HTTPS Request with valid endpoint for ActiveInActive")
	public void admin_sends_https_request_with_valid_endpoint_for_active_in_active() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_count_active_inactive);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
	
	// It will run for all the three role Ids 1 by 1.
	@Given("Admin creates GET Request with {string}")
	public void admin_creates_get_request_with(String roleId) {
		LoggerLoad.info("User creates get request");
		get_count_active_inactive_roleid=reuseVariables.baseURL+"/users/byStatus?id="+roleId;
	}
	
	@When("Admin sends HTTPS Request with valid endpoint for ActiveInActive with RoleId")
	public void admin_sends_https_request_with_valid_endpoint_for_active_in_active_with_role_id() {		
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_count_active_inactive_roleid);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
	
	//GetAdminsbyProgramBatches 
	
	@Given("Admin creates GET Request with valid programbatch Id")
	public void admin_creates_get_request_with_valid_programbatch_id() {
		LoggerLoad.info("User creates get request");
		get_admin_by_batchId=reuseVariables.baseURL+"/users/programBatch/8744";  //batchId to be passed
		get_admin_by_programId=reuseVariables.baseURL+"/users/programs/16857";  //Program to be passed
		
	}
	//Get Program by BatchId
	@When("Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches")
	public void admin_sends_https_request_with_valid_endpoint_for_adminsby_program_batches() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_admin_by_batchId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
	
	
  //GetAdmin by Program Id
	@When("Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches program")
	public void admin_sends_https_request_with_valid_endpoint_for_adminsby_program_batches_program() {
		System.out.println("Inside ProgramId");
		resBody=auth_req_post.when().get(get_admin_by_programId);
		LoggerLoad.info("Response body : " +resBody.asString());
	}
	
	@Given("Admin creates GET Request with valid role ID")
	public void admin_creates_get_request_with_valid_role_id() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
		LoggerLoad.info("User creates get request");
		get_admin_by_roleId=reuseVariables.baseURL+"/users/roles/"+hm.get(0).get("AdminRoleId");;      
	}

	@When("Admin sends HTTPS Request with valid endpoint for AdminsbyRoleId")
	public void admin_sends_https_request_with_valid_endpoint_for_adminsby_role_id() {
		LoggerLoad.info("Sending the Valid EndPoint");	
		System.out.println(get_admin_by_roleId);		
		resBody=auth_req_post.when().get(get_admin_by_roleId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
	}
}
