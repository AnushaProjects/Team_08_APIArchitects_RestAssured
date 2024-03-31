package step_definition_team08;


import utilities_team08.LoggerLoad;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload_team08.UserPayload;
import payload_team08.UserReqBdyUserLoginPayload;
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
	UserPayload userpayload;
	UserReqBdyUserLoginPayload userLogin;
	
	
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
	JSONObject userBody;
	String exist_phone;
	String exist_email;
	public static String invalid_or_empty_data;
	
	
	
	//Creating UserId with Roles
	
	
	//with mandatory fields only
	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
		userpayload = userReqbody.createUserRequest(hm);
		userBody=new JSONObject(userpayload);
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);	
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		System.out.println(userpayload);
		System.out.println(prop.getProperty("bearer"));
		userResponse= auth_req_post.body(userBody.toString()).when().post(baseURL+"/users/roleStatus");
		userResponse.prettyPrint();
//		JsonPath exist1 = userResponse.jsonPath();
//		exist_phone = exist1.get("userPhoneNumber");
//		System.out.println("phoooooneeee"+exist_phone);
		
		//LoggerLoad.info("Retrieving the bearer token from ResponseBody - " +bearerToken);
//		configreader.writingdata("existphone",exist_phone);
//		LoggerLoad.info("Writing the bearer token to config properties - ");
//		LoggerLoad.info("BearerToken - "+bearerToken);
//		JsonPath exist = userResponse.jsonPath();
//		exist_phone = exist.get("userPhoneNumber").toString();
//		configreader.writingdata("existphone",exist_phone);
//		exist_email = exist.get("userLoginEmail");
//		configreader.writingdata("existemail",exist_email);
	}
	
	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer created) {
		LoggerLoad.info("check userId gets generated for Admin role");
		cv.headervalidations(userResponse);
        cv.statusValidations(userResponse,created);
        cv.schemavalidation(userResponse,"/User_json/post_user_json" );
       
        //Data Validations
        
        assertEquals(userpayload.getUserComments(),userResponse.jsonPath().get("userComments"));
        assertEquals(userpayload.getUserFirstName(),userResponse.jsonPath().get("userFirstName"));
        assertEquals(userpayload.getUserLastName(),userResponse.jsonPath().get("userLastName"));
        assertEquals(userpayload.getUserTimeZone(),userResponse.jsonPath().get("userTimeZone"));
        assertEquals(userpayload.getUserLinkedinUrl(),userResponse.jsonPath().get("userLinkedinUrl"));
        assertEquals(userpayload.getUserVisaStatus(),userResponse.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserPhoneNumber(),userResponse.jsonPath().get("userPhoneNumber").toString());
        userLogin=userpayload.getUserLogin();
        assertEquals(userLogin.getUserLoginEmail(),userResponse.jsonPath().get("userLoginEmail"));
        assertEquals(userpayload.getUserVisaStatus(),userResponse.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserEduUg(),userResponse.jsonPath().get("userEduUg"));
        assertEquals(userpayload.getUserEduPg(),userResponse.jsonPath().get("userEduPg"));
        
        
        LoggerLoad.info("UserId Created - "+userResponse.statusCode());
		JsonPath userId = userResponse.jsonPath();
		userIds = userId.get("userId");

		LoggerLoad.info("UserId created with MandatoryField - " +userIds);
		configreader.writingdata("user_id_with_madatory_field",userIds);	
		
		
		
	}
	
	
	
	//Creating UserId with Roles
	//with mandatory fields and Additional Fields
	
	@Given("Admin creates POST request with all mandatory fields and additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		userpayload = userReqbody.createUserRequest(hm);
		userBody=new JSONObject(userpayload);
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
	}
	
	@Then("Admin receives {int} Created Status with response body and Save userId for {string}.")
	public void admin_receives_created_status_with_response_body_and_save_user_id_for(Integer created, String Scenario) {
		LoggerLoad.info("check userId gets generated for Admin role");
		
		cv.headervalidations(userResponse);
        cv.statusValidations(userResponse,created);
        cv.schemavalidation(userResponse,"/User_json/post_user_json" );
		
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
	
	//Invalid Values
	@Given("Admin creates POST request with all mandatory fields and additional {string} {string}")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional(String field, String invalidvalues) throws InvalidFormatException, IOException {
	    LoggerLoad.info("Admin creates POST request with all mandatory fields and additional");
			List<Map<String, String>> hm=read.getData(path,"UserModule");
			userpayload = userReqbody.createUserRequest(hm);
			if(field.equalsIgnoreCase("fieldsInvalid"))
			{
				invalid_or_empty_data=hm.get(0).get("InvalidValue");
				LoggerLoad.info("Create userId with Invalid values");
			}
			if(invalidvalues.equalsIgnoreCase("fieldsmissing"))
			{
				System.out.println("Inside the mising Step definition");
				invalid_or_empty_data="";
				LoggerLoad.info("Create userId with missing mandatory field");
			}
			userReqbody.negativeUserScenario(invalidvalues, invalid_or_empty_data);
			userBody=new JSONObject(userpayload);
			
			
			LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
		}

		@Then("Admin receives {int} Bad Request Status with message and boolean success details")
		public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer badreq) {
			LoggerLoad.info("check userId gets generated for Admin role");
			cv.headervalidations(userResponse);
	        cv.statusValidations(userResponse,badreq);
	        LoggerLoad.info("UserId is not created");
	        
		}
		
		@When("Admin sends HTTPS Request with endpoint Unauthorized")
		public void admin_sends_https_request_with_endpoint_unauthorized() {
			//userResponse= noauth_req_post.body(userBody.toString()).when().post(baseURL+"/users/roleStatus");
			userResponse= noauth_req_post.body(userBody.toString()).when().post(baseURL+"/users/roleStatus");
			userResponse.prettyPrint();
		}

		@Then("Admin receives status {int} with Unauthorized message")
		public void admin_receives_status_with_unauthorized_message(Integer unauthorized) {
			LoggerLoad.info("check userId gets generated for Admin role with no auth");
			System.out.println(userResponse);
			cv.headervalidations(userResponse);
	        cv.statusValidations(userResponse,unauthorized);
	        LoggerLoad.info("UserId is not created");
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
		LoggerLoad.info("Get All the available roles");
		resBody=auth_req_post.when().get(get_all_roles);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("All the available roles are displayed");
	}

	@Then("Admin receives {int} OK")
	public void admin_receives_ok(Integer successcode) {
//		System.out.println("checking for status code : "+userResponse.getStatusCode());
		cv.headervalidations(resBody);
        cv.statusValidations(resBody,successcode);
//     	cv.schemavalidation(userResponse,"/User_json/post_user_json" );
        LoggerLoad.info("All the user Roles will be displayed");
		
	}
	
	//GET All Admins
	
	@When("Admin sends HTTPS Request with valid endpoint for AllAdmins")
	public void admin_sends_https_request_with_valid_endpoint_for_all_admins() {
		LoggerLoad.info("Sending the Valid EndPoint");		
		LoggerLoad.info("Get All Admins");
		resBody=auth_req_post.when().get(get_all_admins);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("All the admin users will be displayed");
	}

	@Then("Admin receives {int} OK Status with response body")
	public void admin_receives_ok_status_with_response_body(Integer successcode) {
		cv.headervalidations(resBody);
        cv.statusValidations(resBody,successcode);
//     	cv.schemavalidation(userResponse,"/User_json/post_user_json" );
	}
	
	//Get Admin with AdminId
	
	@Given("Admin creates GET Request with valid AdminId")
	public void admin_creates_get_request_with_valid_admin_id() {
		LoggerLoad.info("Get the Admin with Admin Id");
		LoggerLoad.info("User creates get request");
		get_admin_by_userId=reuseVariables.baseURL+"/users/"+prop.getProperty("user_id_with_All_field");    //user_id_with_All_field
		
	}
	
	@When("Admin sends HTTPS Request with valid endpoint for AdminByID")
	public void admin_sends_https_request_with_valid_endpoint_for_admin_by_id() {
		LoggerLoad.info("Get the Admin with Admin Id");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_admin_by_userId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("Admin get displayed with give AdminId ");
	}
	
	//Get Active Admin
	@When("Admin sends HTTPS Request with valid endpoint for ActiveAdmin")
	public void admin_sends_https_request_with_valid_endpoint_for_active_admin() {
		LoggerLoad.info("Get the Active admins");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_all_active_admins);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("All the Active admin will be displayed");
	}
	
	//GET CountOf Active/InActive
	
	@When("Admin sends HTTPS Request with valid endpoint for ActiveInActive")
	public void admin_sends_https_request_with_valid_endpoint_for_active_in_active() {
		LoggerLoad.info("Get the CountOf Active/InActive");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_count_active_inactive);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("CountOf Active/InActive will be displayed");
	}
	
	// It will run for all the three role Ids 1 by 1.
	@Given("Admin creates GET Request with {string}")
	public void admin_creates_get_request_with(String roleId) {
		LoggerLoad.info("User creates get request");
		get_count_active_inactive_roleid=reuseVariables.baseURL+"/users/byStatus?id="+roleId;
	}
	
	@When("Admin sends HTTPS Request with valid endpoint for ActiveInActive with RoleId")
	public void admin_sends_https_request_with_valid_endpoint_for_active_in_active_with_role_id() {		
		LoggerLoad.info("CountOf Active/InActive by roleId");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_count_active_inactive_roleid);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("CountOf Active/InActive will be displayed by roleId");
		
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
		LoggerLoad.info("Get the Program by Batch Id");
		LoggerLoad.info("Sending the Valid EndPoint");	
		resBody=auth_req_post.when().get(get_admin_by_batchId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("Get the Program by Batch Id is displayed");
	}
	
	
  //GetAdmin by Program Id
	@When("Admin sends HTTPS Request with valid endpoint for AdminsbyProgramBatches program")
	public void admin_sends_https_request_with_valid_endpoint_for_adminsby_program_batches_program() {
		LoggerLoad.info("Get the Program by Program Id");
		resBody=auth_req_post.when().get(get_admin_by_programId);
		LoggerLoad.info("Response body : " +resBody.asString());
		LoggerLoad.info("Get the Program by Program Id is displayed");
	}
	
	@Given("Admin creates GET Request with valid role ID")
	public void admin_creates_get_request_with_valid_role_id() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
		LoggerLoad.info("User creates get request");
		get_admin_by_roleId=reuseVariables.baseURL+"/users/roles/"+hm.get(0).get("AdminRoleId");;      
	}

	@When("Admin sends HTTPS Request with valid endpoint for AdminsbyRoleId")
	public void admin_sends_https_request_with_valid_endpoint_for_adminsby_role_id() {
		LoggerLoad.info("Get the Admins by Role Id");
		LoggerLoad.info("Sending the Valid EndPoint");	
		System.out.println(get_admin_by_roleId);		
		resBody=auth_req_post.when().get(get_admin_by_roleId);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("Get the Admins by Role Id is displayed");
	}
}