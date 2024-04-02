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
import payload_team08.UpdateUserIdPayload;
import payload_team08.UserPayload;
import payload_team08.UserReqBdyUserLoginPayload;
import payload_team08.UserStatusPayload;
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
	UserStatusPayload usp;
	UserReqBdyUserLoginPayload userLogin;
	UpdateUserIdPayload updaterole;
	
	
	
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
	String userBody;
	String get_count_active_inactive_invalidroleid;
	String get_Admin_by_invalid_batch;
	String get_admin_by_invalidprogramId;
	String get_admin_by_invalidroleId;
	String delete_userId;
	public static String exist_phone;
	public static String exist_email;
	String given_field;
	public static String invalid_or_empty_data;
	List<Map<String, String>> hm;
	
	
	
	//Creating UserId with Roles
	
	
	//with mandatory fields only
	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws InvalidFormatException, IOException {
		System.out.println("Inside");
		userpayload=userReqbody.returnUserPayload("UserModuleMandatory");
		userBody=userReqbody.convertJsonToString(userpayload);
		System.out.println(userBody);
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);	
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		System.out.println(userpayload);
		System.out.println(prop.getProperty("bearer"));
		System.out.println("Inside when");
		System.out.println("Request Sending: "+userBody);
		resBody= auth_req_post.body(userBody).when().post(baseURL+user_post_endpoint);
		resBody.prettyPrint();
	}
	
	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer created) {
		LoggerLoad.info("check userId gets generated for Admin role");
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, created, "");
        cv.schemavalidation(resBody,"/User_json/post_user_json" );
       
        //Data Validations
        
        assertEquals(userpayload.getUserComments(),resBody.jsonPath().get("userComments"));
        assertEquals(userpayload.getUserFirstName(),resBody.jsonPath().get("userFirstName"));
        assertEquals(userpayload.getUserLastName(),resBody.jsonPath().get("userLastName"));
        assertEquals(userpayload.getUserTimeZone(),resBody.jsonPath().get("userTimeZone"));
        assertEquals(userpayload.getUserLinkedinUrl(),resBody.jsonPath().get("userLinkedinUrl"));
        assertEquals(userpayload.getUserVisaStatus(),resBody.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserPhoneNumber(),resBody.jsonPath().get("userPhoneNumber").toString());
        userLogin=userpayload.getUserLogin();
        assertEquals(userLogin.getUserLoginEmail(),resBody.jsonPath().get("userLoginEmail"));
        assertEquals(userpayload.getUserVisaStatus(),resBody.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserEduUg(),resBody.jsonPath().get("userEduUg"));
        assertEquals(userpayload.getUserEduPg(),resBody.jsonPath().get("userEduPg"));
        
        
        LoggerLoad.info("UserId Created - "+resBody.statusCode());
		JsonPath userId = resBody.jsonPath();
		userIds = userId.get("userId");

		LoggerLoad.info("UserId created with MandatoryField - " +userIds);
		configreader.writingdata("user_id_with_madatory_field",userIds);	
		
		
		
	}
	
	//Creating UserId with Roles
	//Using Existing EmailId and PhoneNumber
	
	
	@Given("Admin creates POST request with all mandatory fields with ExistingValues")
	public void admin_creates_post_request_with_all_mandatory_fields_with_existing_values() throws InvalidFormatException, IOException {
		System.out.println("Inside Given");
		userpayload=userReqbody.returnUserPayload("UserModuleMandatory");
		userBody=userReqbody.convertJsonToString(userpayload);
		System.out.println(userBody);
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
		System.out.println("Check");
	}

	@When("Admin sends HTTPS Request with endpoint to save {string}")
	public void admin_sends_https_request_with_endpoint_to_save(String existingValue) throws InvalidFormatException, IOException {
	
		userpayload=userReqbody.returnUserPayload("UserModuleMandatory");
		
		switch(existingValue) {
		case "Create":
			System.out.println("Create");
			userBody=userReqbody.convertJsonToString(userpayload);
			resBody= auth_req_post.body(userBody).when().post(baseURL+user_post_endpoint);
			resBody.prettyPrint();
			JsonPath exist = resBody.jsonPath();
			exist_email=exist.get("userLoginEmail");
			JsonPath existphone = resBody.jsonPath();
			System.out.println(exist_email); 
			exist_phone=existphone.get("userPhoneNumber").toString();
			System.out.println(exist_phone); 
			break;
		case "ExistingPhoneumber":
			System.out.println("ExistingPhoneumber");
			userpayload.setUserPhoneNumber(exist_phone);
			LoggerLoad.info("Existing Phone = " +exist_phone );
			break;
		case "ExistingEmailId":
			System.out.println("ExistingEmailId");
			System.out.println(reuseMethods.autoPhoneNumber());
			userpayload.setUserPhoneNumber(reuseMethods.autoPhoneNumber());
			userLogin=userpayload.getUserLogin();
			userLogin.setUserLoginEmail(exist_email);
			LoggerLoad.info("Existing EmailId = " +exist_email );
			break;
		}
		userBody=userReqbody.convertJsonToString(userpayload);
		resBody= auth_req_post.body(userBody).when().post(baseURL+user_post_endpoint);
		resBody.prettyPrint();

	}
		
	@Then("Admin receives {int} Created Status with response body for {string}")
	public void admin_receives_created_status_with_response_body_for(Integer existing, String value) {
		LoggerLoad.info("check userId gets generated for Admin role");
		if(value.equalsIgnoreCase("create")) {
			System.out.println("Inside If");
		}
		else { 
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, existing, "");
		cv.messageValidations(resBody, false);
       // cv.schemavalidation(userResponse,"/User_json/post_user_json" );
		}
	}
	
	
	
	
	//Creating UserId with Roles
	//with mandatory fields and Additional Fields
	
	@Given("Admin creates POST request with all mandatory fields and additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws InvalidFormatException, IOException {
		userpayload=userReqbody.returnUserPayload("UserModule");
		userBody=userReqbody.convertJsonToString(userpayload);
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
	}
	
	@Then("Admin receives {int} Created Status with response body and Save userId for {string}.")
	public void admin_receives_created_status_with_response_body_and_save_user_id_for(Integer created, String Scenario) {
		LoggerLoad.info("check userId gets generated for Admin role");
		
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, created, "");
        cv.schemavalidation(resBody,"/User_json/post_user_json" );
        
        assertEquals(userpayload.getUserComments(),resBody.jsonPath().get("userComments"));
        assertEquals(userpayload.getUserFirstName(),resBody.jsonPath().get("userFirstName"));
        assertEquals(userpayload.getUserLastName(),resBody.jsonPath().get("userLastName"));
        assertEquals(userpayload.getUserTimeZone(),resBody.jsonPath().get("userTimeZone"));
        assertEquals(userpayload.getUserLinkedinUrl(),resBody.jsonPath().get("userLinkedinUrl"));
        assertEquals(userpayload.getUserVisaStatus(),resBody.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserPhoneNumber(),resBody.jsonPath().get("userPhoneNumber").toString());
        userLogin=userpayload.getUserLogin();
        assertEquals(userLogin.getUserLoginEmail(),resBody.jsonPath().get("userLoginEmail"));
        assertEquals(userpayload.getUserVisaStatus(),resBody.jsonPath().get("userVisaStatus"));
        assertEquals(userpayload.getUserEduUg(),resBody.jsonPath().get("userEduUg"));
        assertEquals(userpayload.getUserEduPg(),resBody.jsonPath().get("userEduPg"));
		
		JsonPath userId = resBody.jsonPath();
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
	
	//WithoutRequestBody
	@When("Admin sends HTTPS Request with endpoint without Requestbody")
	public void admin_sends_https_request_with_endpoint_without_requestbody() {
		LoggerLoad.info("Sending the valkid endpoint without Requestbody");
		resBody= auth_req_post.body("").when().post(baseURL+user_post_endpoint);
		resBody.prettyPrint();
	}
	
	//Invalid Values
	@Given("Admin creates POST request with all mandatory fields and additional {string} {string}")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional(String field, String invalidvalues) throws InvalidFormatException, IOException {
	    
		LoggerLoad.info("Admin creates POST request with all mandatory fields and additional");
	    given_field="The InvalidValue for " +invalidvalues;
			List<Map<String, String>> hm=read.getData(path,"UserModule");
			//userBody=userReqbody.json_body("UserMOdule");
			
			if(field.equalsIgnoreCase("fieldsInvalid"))
			{
				System.out.println("Inside Given");
				invalid_or_empty_data=hm.get(0).get("InvalidValue");
				System.out.println(invalid_or_empty_data);
				LoggerLoad.info("Create userId with Invalid values");
			}
			if(invalidvalues.equalsIgnoreCase("fieldsmissing"))
			{
				System.out.println("Inside the mising Step definition");
				invalid_or_empty_data="";
				LoggerLoad.info("Create userId with missing mandatory field");
			}
			userBody = userReqbody.negativeUserScenario(invalidvalues, invalid_or_empty_data);
			
			System.out.println("Request Generated for Negative scenarion:");
			System.out.println(userBody);
			
			LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
		}

		@Then("Admin receives {int} Bad Request Status with message and boolean success details")
		public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer badreq) {
			LoggerLoad.info("check userId gets generated for Admin role");
			cv.headervalidations(resBody);
			cv.statusValidations(resBody, badreq, "Status validation");
	        //cv.statusValidations(invalid_or_empty_data,userResponse,badreq);;
	        LoggerLoad.info("UserId is not created");
	        
		}
		
		@When("Admin sends HTTPS Request with endpoint Unauthorized")
		public void admin_sends_https_request_with_endpoint_unauthorized() {
			LoggerLoad.info("Sending the Request UnAuthorised");
			resBody= noauth_req_post.body(userBody).when().post(baseURL+user_post_endpoint);
			resBody.prettyPrint();
		}

		@Then("Admin receives status {int} with Unauthorized message")
		public void admin_receives_status_with_unauthorized_message(Integer unauthorized) {
			LoggerLoad.info("check userId gets generated for Admin role with no auth");
			System.out.println(resBody);
			cv.statusValidations(resBody, unauthorized, "");
	        LoggerLoad.info("UserId is not created");
		}

	//Check if admin is able to retrieve all the available roles
	
	@Given("Admin creates GET Request")
	public void admin_creates_get_request() {
		LoggerLoad.info("User creates get request");
		get_all_roles=reuseVariables.baseURL+getroles;
		get_all_admins=reuseVariables.baseURL+allusers;
		get_all_active_admins=reuseVariables.baseURL+allactive;
		get_count_active_inactive=reuseVariables.baseURL+count_active_inactive;
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
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, successcode, "");
     	//cv.schemavalidation(resBody,"/User_json/get_all_roles_json");
        LoggerLoad.info("All the user Roles will be displayed");
		
	}
	//Get Unauthorised
	@When("Admin sends HTTPS Request with GET Unauthorized {string}")
	public void admin_sends_https_request_with_get_unauthorized(String getrequests) {
		LoggerLoad.info("Sending the Request UnAuthorised");
		
		if(getrequests.equalsIgnoreCase("GetAllRoles")) {
			resBody= noauth_req_post.when().get(get_all_roles);}
		
		else if(getrequests.equalsIgnoreCase("GetAllAdmins")) {
			resBody= noauth_req_post.when().get(get_all_admins);}
		
		else if(getrequests.equalsIgnoreCase("GetAdminbyId")) {
			resBody= noauth_req_post.when().get(get_admin_by_userId);}
		
		else if(getrequests.equalsIgnoreCase("GetAllActiveAdmin")) {
			resBody= noauth_req_post.when().get(get_all_active_admins);}
		
		else if(getrequests.equalsIgnoreCase("CountActiveInActive")) {
			resBody= noauth_req_post.when().get(get_count_active_inactive);}
		
		else if(getrequests.equalsIgnoreCase("UserbyBatch")) {
			resBody= noauth_req_post.when().get(get_admin_by_batchId);}
		
		else if(getrequests.equalsIgnoreCase("UserbyProgram")) {
			resBody= noauth_req_post.when().get(get_admin_by_programId);}
		
		else if(getrequests.equalsIgnoreCase("UserbyRoleId")) {
			resBody= noauth_req_post.when().get(get_admin_by_roleId);}
		
		resBody.prettyPrint();
	}

	@Then("Admin receives status {int} with message")
	public void admin_receives_status_with_message(Integer statuscode) {
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, statuscode, "Error Message");
        LoggerLoad.info("All the user Roles will not be displayed");
	}
	
	//Invalid endpoint
	@When("Admin sends HTTPS Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() {
		resBody=auth_req_post.when().get(invalid_endpoint);    //invalid endpoint from reusable variables 
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

	@Then("Admin receives {int} OK Status with response body {string}")
	public void admin_receives_ok_status_with_response_body(Integer successcode, String getrequest) {
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, successcode, "Success Ok");
		if(getrequest.equalsIgnoreCase("AllAdmins")) {
		//cv.schemavalidation(resBody,"/User_json/get_all_admins_json");
		}
		else if(getrequest.equalsIgnoreCase("AdminByID")) {
		cv.schemavalidation(resBody,"/User_json/get_all_admins_by_id_json");
		}
		else if(getrequest.equalsIgnoreCase("AllActiveAdmins")) {
		//cv.schemavalidation(resBody,"/User_json/get_all_active_admins_json");
		}
		else if(getrequest.equalsIgnoreCase("CountActiveInActive")) {
			cv.schemavalidation(resBody,"/User_json/get_count_active_inactive_json");
			}
		else if(getrequest.equalsIgnoreCase("CountActiveInActivebyRole")) {
			cv.schemavalidation(resBody,"/User_json/get_count_active_inactive_by _roleid_json");
			}
		else if(getrequest.equalsIgnoreCase("ProgramBatches")) {
			cv.schemavalidation(resBody,"/User_json/get_admin_by_programbatches_json");
			}
		else if(getrequest.equalsIgnoreCase("Program")) {
			cv.schemavalidation(resBody,"/User_json/get_admin_by_programId_json");
			}
		else if(getrequest.equalsIgnoreCase("Role")) {
//			cv.schemavalidation(resBody,"/User_json/get_admin_with_roles_json");
			}
	}
	
	//Get Admin with AdminId
	
	@Given("Admin creates GET Request with {string} AdminId")
	public void admin_creates_get_request_with_admin_id(String givenUserid) {
		LoggerLoad.info("Get the Admin with Admin Id");
		LoggerLoad.info("User creates get request");
		if(givenUserid.equalsIgnoreCase("valid")) {
		get_admin_by_userId=reuseVariables.baseURL+allusers+prop.getProperty("user_id_with_All_field");    //getting user id from confi.propertfile
		}
		else if(givenUserid.equalsIgnoreCase("Invalid")) {
		get_admin_by_userId=reuseVariables.baseURL+"/users/"+invalid_Id; 	//getting invalid userid fron reusable variables
		}
		
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
		LoggerLoad.info("User creates get request to get count of Active and Inactive by RoleId : " +roleId);
		get_count_active_inactive_roleid=reuseVariables.baseURL+count_by_id+roleId;
	}
	
	@When("Admin sends HTTPS Request with valid endpoint with {string}")
	public void admin_sends_https_request_with_valid_endpoint_with(String ids) {	
		if(ids.equalsIgnoreCase("RoleId")){
		LoggerLoad.info("CountOf Active/InActive by roleId");
		LoggerLoad.info("Sending the Valid EndPoint");		
		resBody=auth_req_post.when().get(get_count_active_inactive_roleid);
		LoggerLoad.info("Response body : " +resBody.asString()); 
		LoggerLoad.info("CountOf Active/InActive will be displayed by roleId");
		}
		else if(ids.equalsIgnoreCase("InvalidRoleId")) {
			LoggerLoad.info("CountOf Active/InActive by InvalidroleId");
			resBody=auth_req_post.when().get(get_count_active_inactive_invalidroleid);
			LoggerLoad.info("Response body : " +resBody.asString()); 
			LoggerLoad.info("CountOf Active/InActive will not be displayed by Invalid roleId");
		}
		else if(ids.equalsIgnoreCase("InvalidBatchId")) {
			LoggerLoad.info("Get the user with Invalid batchId");
			resBody=auth_req_post.when().get(get_Admin_by_invalid_batch);
			LoggerLoad.info("Response body : " +resBody.asString()); 
			LoggerLoad.info("CountOf Active/InActive will not be displayed by Invalid roleId");
		}
		else if(ids.equalsIgnoreCase("InvalidProgramId")) {
			LoggerLoad.info("Get the user with Invalid batchId");
			resBody=auth_req_post.when().get(get_admin_by_invalidprogramId);
			LoggerLoad.info("Response body : " +resBody.asString()); 
			LoggerLoad.info("CountOf Active/InActive will not be displayed by Invalid roleId");
		}
		else if(ids.equalsIgnoreCase("InvalidRoleIds")) {
			LoggerLoad.info("Get the user with Invalid RoleId");
			resBody=auth_req_post.when().get(get_admin_by_invalidroleId);
			LoggerLoad.info("Response body : " +resBody.asString()); 
			LoggerLoad.info("CountOf Active/InActive will not be displayed by Invalid roleId");
		}
		
		
	}
	@Given("Admin creates GET Request with {string} scenario")
	public void admin_creates_get_request_with_scenario(String invalidId) {
		if(invalidId.equalsIgnoreCase("InvalidroleId")) {
		LoggerLoad.info("User creates get request to get count of Active and Inactive by Invalid RoleId : " +invalid_Id);
		get_count_active_inactive_invalidroleid=reuseVariables.baseURL+count_by_id+invalid_Id;  //invalid id from reusable variable
		}
		if(invalidId.equalsIgnoreCase("InvalidbatchId")) {
		LoggerLoad.info("User creates get request to get the Admins by program batches for invalid batch ID: " +invalid_integer_Id);
		get_Admin_by_invalid_batch=reuseVariables.baseURL+userbatch+invalid_integer_Id;  //invalid id from reusable variable
		}
		
	}

	
	
	//GetAdminsbyProgramBatches 
	
	@Given("Admin creates GET Request with {string} programbatch Id")
	public void admin_creates_get_request_with_programbatch_id(String ids) {
		if(ids.equalsIgnoreCase("valid")) {
		LoggerLoad.info("User creates get request to pass the batch and program iD");
		get_admin_by_batchId=reuseVariables.baseURL+"/users/programBatch/10015";  //batchId to be passed
		get_admin_by_programId=reuseVariables.baseURL+userprogram+"17874";  //ProgramId is getting passed from config.properties prop.getProperty("program_Id_chaining")
		}
		else if(ids.equalsIgnoreCase("invalid")) {
			LoggerLoad.info("User creates get request to pass the Invalic ProgramId");
			get_admin_by_invalidprogramId=reuseVariables.baseURL+userprogram+invalid_integer_Id;//invalid id from reusable variables.
		}
		
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
	
	@Given("Admin creates GET Request with {string} Role Id")
	public void admin_creates_get_request_with_role_id(String roleId) throws InvalidFormatException, IOException {
		if(roleId.equalsIgnoreCase("valid")) {
		List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
		LoggerLoad.info("User creates get request to display admin by role id " +hm.get(0).get("AdminRoleId"));
		get_admin_by_roleId=reuseVariables.baseURL+userrole+hm.get(0).get("AdminRoleId"); 
		LoggerLoad.info("url with validroleId - "+get_admin_by_roleId);
		}
		if(roleId.equalsIgnoreCase("invalid")) {
		
		LoggerLoad.info("User creates get request to display admin by role id " +invalid_Id);
		get_admin_by_invalidroleId=reuseVariables.baseURL+userrole+invalid_Id; 
		LoggerLoad.info("url with InvalidEndPoint - "+get_admin_by_invalidroleId);
		}
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
	
	//Update User Info
	@Given("Admin creates Update Admin Role ID Request with valid userRoleList in request body user")
	public void admin_creates_update_admin_role_id_request_with_valid_user_role_list_in_request_body_user() throws InvalidFormatException, IOException {
		userpayload=userReqbody.returnUserPayloadput("UserModule");
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		
		userBody=userReqbody.convertJsonToString(userpayload);
		LoggerLoad.info("Converted ProgramRequestBody for Updating ProgramId to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint user")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint_user() {
		LoggerLoad.info("Request Sending: "+userBody);
		
		resBody= auth_req_post.body(userBody).when().put(baseURL+update_user);
		LoggerLoad.info("The Request :" +baseURL+update_user);
		resBody.prettyPrint();
	}

	@Then("Admin receives {int} OK Status with {string} response body user")
	public void admin_receives_ok_status_with_response_body_user(Integer statuscode, String string) {
		cv.headervalidations(resBody);
		cv.statusValidations(resBody, statuscode, "");
        cv.schemavalidation(resBody,"/User_Json/update_user_json" );
	}
	
	//Update user with Invalid
	@Given("Check if admin is able to update a Admin with valid endpoint and missing fields in request body {string}")
	public void check_if_admin_is_able_to_update_a_admin_with_valid_endpoint_and_missing_fields_in_request_body(String InvalidValues) throws InvalidFormatException, IOException {
LoggerLoad.info("Create userId with Invalid values");
		
		userBody = userReqbody.putegativeUserScenario(InvalidValues);
		
		LoggerLoad.info("Request Generated for Negative scenarion:");
		LoggerLoad.info(userBody);
		
		LoggerLoad.info("Converted UserRequestBody for Creating USErId role to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint() {
		LoggerLoad.info("Updating the endpoint with Request body with Invalid values");
		resBody= auth_req_post.body(userBody).when().put(baseURL+update_user_invalid);
		System.out.println(baseURL+update_user_invalid);
		resBody.prettyPrint();
	}
	
	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint user no auth")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint_user_no_auth() {
		LoggerLoad.info("Request Sending without Authorisation "+userBody);
		
		resBody= noauth_req_post.body(userBody).when().put(baseURL+update_user);
		LoggerLoad.info("The Request :" +baseURL+update_user);
		resBody.prettyPrint();	
	}

	//update Role Status
	@Given("Admin creates Update Admin Role ID Request for role status")
	public void admin_creates_update_admin_role_id_request_for_role_status() throws InvalidFormatException, IOException {
		usp=userReqbody.returnUpdateRoleStatus("UserModule");
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		
		userBody=userReqbody.convertJsonToString2(usp);
		LoggerLoad.info("Converted ProgramRequestBody for Updating ProgramId to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint rolestatus")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint_rolestatus() {
		LoggerLoad.info("Request Sending: "+userBody);
		
		resBody= auth_req_post.body(userBody).when().put(baseURL+"/users/roleStatus/"+prop.getProperty("user_id_with_All_field"));
		LoggerLoad.info("The Request :" +baseURL+"/users/roleStatus/"+prop.getProperty("user_id_with_All_field"));
		resBody.prettyPrint();
	}
	@Then("Admin receives {int} OK Status with updated message")
	public void admin_receives_ok_status_with_updated_message(Integer statuscode) {
	    cv.statusValidations(resBody, statuscode, "Status");
	}
	
	//UpdateRoleId
	@Given("Admin creates Update Admin Role ID Request for role Id")
	public void admin_creates_update_admin_role_id_request_for_role_id() throws InvalidFormatException, IOException {
		updaterole=userReqbody.returnUpdateRoleId("UserModule");
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		
		userBody=userReqbody.convertJsonToStringid(updaterole);
		LoggerLoad.info("Converted ProgramRequestBody for Updating ProgramId to JSON Format " +userBody);
	}

	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint roleId")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint_role_id() {
LoggerLoad.info("Request Sending: "+userBody);
		
		resBody= auth_req_post.body(userBody).when().put(baseURL+"/users/roleId/"+prop.getProperty("user_id_with_All_field"));
		LoggerLoad.info("The Request :" +baseURL+"/users/roleId/"+prop.getProperty("user_id_with_All_field"));
		resBody.prettyPrint();
	}
	@Given("Admin creates Delete Request for userId")
	public void admin_creates_delete_request_for_user_id() {
		LoggerLoad.info("Deleting the UserId");
		delete_userId=baseURL+delete_userid;
		LoggerLoad.info("The given request" +delete_userId);
	}

	@When("Admin sends HTTPS Request for Delete with Valid Program Id and Valid End Point userId")
	public void admin_sends_https_request_for_delete_with_valid_program_id_and_valid_end_point_user_id() {
		System.out.println(prop.getProperty("program_name"));
		resBody=auth_req_post.when().delete(delete_userId);
		resBody.prettyPrint();
	}

	@Then("Admin receives {int} Ok status with message for Delete userId")
	public void admin_receives_ok_status_with_message_for_delete_user_id(Integer status) {
	    cv.statusValidations(resBody, status, "delete_userId");
	}
}