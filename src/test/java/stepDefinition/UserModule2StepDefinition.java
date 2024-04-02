package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.testng.Assert;

import requestBodyRaw.LoginRequestBody;
import requestBodyRaw.UserRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payLoad.UpdateUserLoginStatusPayload;
import payLoad.UpdateUserRoleIdPayload;
import payLoad.UpdateUserRolePrgmBatchStatusPayload;
import payLoad.UpdateUserRoleStatusPayload;
import payLoad.UserPayload;
import payLoad.UserPutPayload;
import utilities.CommonValidation;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class UserModule2StepDefinition extends ReusableVariables {

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	UserRequestBody userReqbody=new UserRequestBody();
	ExcelReader read = new ExcelReader();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	UpdateUserRoleIdPayload UserRoleIdPayload = new UpdateUserRoleIdPayload();
	CommonValidation cv = new CommonValidation();
	//UserPayload userreqBodyAll;
	Response userResponse;
	String userIds;
	String emailId;
	String roleId;
	String userBody;
	Response resBody;
	UserPayload userpayload;
	UserPutPayload userputpayload;
	UpdateUserRoleStatusPayload userrolestatuspayload;
	UpdateUserRolePrgmBatchStatusPayload userpgrmbatchstatuspayload;
	UpdateUserRoleIdPayload userroleidpayload;
	UpdateUserLoginStatusPayload userloginstatuspayload;
	String get_admin_by_userId;
	String userId = configreader.getUserIdWithAAllField();

	@Given("Admin creates POST request with all mandatory fields and additional fields")
	public void admin_creates_post_request_with_all_mandatory_fields_and_additional_fields() throws InvalidFormatException, IOException {

		//		userpayload = userReqbody.returnUserPayload("UserModule");
		//		userBody=userReqbody.convertJsonToString(userpayload);		
		//System.out.println(userreqBodyAll);		
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		userReqbody = userReqbody.createUserRequest(hm);
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		//System.out.println(userreqBodyAll);
		resBody= reqspec.body(userReqbody).when().post(baseURL+"/users/roleStatus");				
	}

	@Then("Admin receives {int} Created Status with response body and Save userId for {string}.")
	public void admin_receives_created_status_with_response_body_and_save_user_id_for(int created, String Scenario) {
		System.out.println(resBody.statusCode());
		Assert.assertEquals(resBody.statusCode(), created);

		JsonPath jsonres = resBody.jsonPath();
		userIds = jsonres.get("userId");
		emailId = jsonres.get("userLoginEmail");
		roleId = jsonres.getString("");

		if(Scenario.equalsIgnoreCase("Positive")){
			System.out.println("UserId with Admin Role for Valid Scenarios - "+userIds  );
			configreader.writingdata("user_id_with_All_field",userIds);
			configreader.writingdata("email_id_used_for_creating_userId",emailId);
		}
		else if(Scenario.equalsIgnoreCase("Negative")){
			System.out.println("UserId with Admin Role to use it in Negative Scenarios - " +userIds);
			configreader.writingdata("negative_scenerio_user_Id",userIds);
		}
	}	

	//GET Request by Admin ID
	@When("User  sends HTTPS Request with {string}")
	public void user_sends_https_request_with(String endpoint) {
		if(endpoint.equalsIgnoreCase("valid endpoint")) {
			resBody= given().header("Content-Type","application/json")
					.header("Authorization","Bearer " + prop.getProperty("bearer"))
					.when().get(baseURL+"/users/roles");
			//System.out.println(userGetResponse.asPrettyString());
		}

		if(endpoint.equalsIgnoreCase("invalid endpoint")){
			resBody = given().header("Content-Type","application/json")
					.header("Authorization","Bearer " + prop.getProperty("bearer"))
					.when().get(baseURL+"/users/rolers");
			//System.out.println(userGetResponse.asPrettyString());
		}
	}

	@Then("Admin receives {int} OK Status with {string} response body")
	public void admin_receives_ok_status_with_response_body(int statuscode, String method) {
		Assert.assertEquals(resBody.statusCode(), statuscode);
		cv.headervalidations(resBody);
		//cv.statusValidations(resBody, statuscode, "Error Message");

		if(method.equalsIgnoreCase("PUT - User"))
			cv.schemavalidation(resBody, "/Put_User_JSON");
		//else if(method.equalsIgnoreCase("DELETE - User"))
		//resBody = reqspec.pathParam("userId", invalid_Id).when().delete(baseURL+"/users/{userId}");
		//else if(method.equalsIgnoreCase("GET - Admin with filters"))
		//resBody = reqspec.pathParam("userId", invalid_Id).body(userreqBodyAll).when().get(baseURL+"v2/users");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role ID"))
			cv.schemavalidation(resBody, "/Put_User_RoleId_JSON");
		else if(method.equalsIgnoreCase("PUT - Update Admin role status by Admin ID"))
			cv.schemavalidation(resBody, "/Put_User_Role_Status_JSON");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role Program Batch status"))
			cv.schemavalidation(resBody, "/Put_User_Role_Program_Batch_Status_JSON");
		else if (method.equalsIgnoreCase("PUT - Update Admin login status"))
			cv.schemavalidation(resBody, "/Put_User_Login_Status_JSON");
	}	

	//PUT Request - User/Admin ID 

	@Given("Admin creates PUT Request with valid data in request body")
	public void admin_creates_put_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {		
		//		userputpayload = userReqbody.returnUserPutPayload("UserModulePUT");				
		//		userBody=userReqbody.convertJsonToString(userputpayload);
		//System.out.println(userreqBodyAll);
	}

	@When("Admin sends HTTPS request with put endpoint")
	public void admin_sends_https_request_with_put_endpoint(){
		String userId = configreader.getUserIdWithAAllField();
		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/{userId}");

	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_ok_status_with_message(int statuscode) {
		//System.out.println(resBody.statusCode());
		Assert.assertEquals(resBody.statusCode(), statuscode);
		cv.headervalidations(userResponse);
		cv.statusValidations(userResponse, statuscode);		
	}

	//PUT Request User Role Status

	@Given("Admin creates Update User Role Status PUT Request with valid data in request body")
	public void admin_creates_update_user_role_status_put_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {

		//		userrolestatuspayload = userReqbody.returnUpdateUserRoleStatusPayload("UserModulePUT_UserRoleStatus");
		//		userBody=userReqbody.convertJsonToString(userrolestatuspayload);		
		//		System.out.println(userreqBodyAll);
	}

	@When("Admin sends HTTPS Request with Update User Role Status endpoint")
	public void admin_sends_https_request_with_update_user_role_status_endpoint() {
		String userId = configreader.getUserIdWithAAllField();
		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleStatus/{userId}");		
	}

	@Then("Admin receives {int} Ok Status with response message")
	public void admin_receives_ok_status_with_response_message(int statuscode) {
		//System.out.println(userResponse.statusCode());
		Assert.assertEquals(resBody.statusCode(), statuscode);
	}	

	// PUT Request (Update User Role Program Batch status)

	@Given("Admin creates Update Admin Role Program Batch status PUT Request with valid data in request body")
	public void admin_creates_update_admin_role_program_batch_status_put_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		//		userpgrmbatchstatuspayload = userReqbody.returnupdateAdminRoleProgramBatchStatus("");
		//		userBody=userReqbody.convertJsonToString(userpgrmbatchstatuspayload);
		//		System.out.println(userreqBodyAll);
	}

	@When("Admin sends HTTPS Request with Update Admin Role Program Batch status endpoint")
	public void admin_sends_https_request_with_update_admin_role_program_batch_status_endpoint() {
		String userId = configreader.getUserIdWithAAllField();
		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleProgramBatchStatus/{userId}");
		resBody.prettyPrint();
	}

	@Then("Admin receives {int} OK Status with response body for Update Admin Role Program Batch status PUT Request")
	public void admin_receives_ok_status_with_response_body_for_update_admin_role_program_batch_status_put_request(int statuscode) {
		//System.out.println(userResponse.statusCode());
		Assert.assertEquals(userResponse.statusCode(), statuscode);
	}

	//PUT Request (Update Admin Role ID)
	@Given("Admin creates Update Admin Role ID Request with valid userRoleList in request body")
	public void admin_creates_update_admin_role_id_request_with_valid_user_role_list_in_request_body() throws InvalidFormatException, IOException {
		//
		//		userroleidpayload = userReqbody.returnupdateUserRoleId("UserModulePUT_RoldId");
		//		userBody=userReqbody.convertJsonToString(UserRoleIdPayload);
		//		System.out.println(userreqBodyAll);
		LoggerLoad.info("Admin creates POST User Request with valid data in request body");
		List<Map<String, String>> phm=read.getData(path,"UserModule");
		//userreqBodyAll= userReqbody.updateUserRoleId(phm);
	}

	@When("Admin sends HTTPS Request with Update Admin Role ID endpoint")
	public void admin_sends_https_request_with_update_admin_role_id_endpoint() {
		String userId = configreader.getUserIdWithAAllField();
		//		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleId/{userId}");		
	}

	//PUT (Update Admin login status)

	@Given("Admin creates Update Admin login status PUT Request with valid data in request body")
	public void admin_creates_update_admin_login_status_put_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		//		userloginstatuspayload = userReqbody.returnupdateUserLoginStatus("UserModulePUT_LoginStatus");
		//		userBody=userReqbody.convertJsonToString(userloginstatuspayload);
		//		System.out.println(userreqBodyAll);
	}

	@When("Admin sends HTTPS Request with Update Admin login status endpoint")
	public void admin_sends_https_request_with_update_admin_login_status_endpoint() {
		String userId = configreader.getUserIdWithAAllField();
		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/userLogin/{userId}");
		resBody.prettyPrint();
	}

	@Then("Admin receives {int} OK")
	public void admin_receives_ok(int statuscode) {
		//System.out.println(userResponse.statusCode());
		Assert.assertEquals(resBody.statusCode(), statuscode);
		cv.headervalidations(resBody);
		//cv.statusValidations(resBody, statuscode, "Error Message");

	}
	//PUT REQUEST  (Update Admin Role ID) NEGATIVE Scenarios	// invalid request body - valid AdminId

	@Given("Admin creates PUT Request with invalid request body")
	public void admin_creates_put_request_with_invalid_request_body() throws InvalidFormatException, IOException {
		//		userroleidpayload = userReqbody.returnupdateInvalidUserRoleId("UserModulePUT");
		//		userBody=userReqbody.convertJsonToString(userroleidpayload);
		//		System.out.println(userreqBodyAll);
	}

	/*@When("Admin sends HTTPS  {string} Request with endpoint")
	public void admin_sends_https_request_with_endpoint(String endpoint) {
		if(endpoint.equalsIgnoreCase("PUT-Update Admin Role ID")) 
			resBody = reqspec.pathParam("userId", userId).body(userreqBodyAll)
			.when().put(baseURL+"/users/roleId/{userId}");
		else if(endpoint.equalsIgnoreCase("PUT-Update Admin role status by Admin ID"))
			resBody = reqspec.pathParam("userId", userId).body(userreqBodyAll)
			.when().put(baseURL+"/users/roleoId/{userId}");			
	}*/

	@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(int statuscode) {
		//System.out.println(resBody.statusCode());
		Assert.assertEquals(resBody.statusCode(), statuscode);
		cv.statusValidations(userResponse, statuscode);
		cv.headervalidations(userResponse);
		//need to do message validation
	}

	// PUT REQUEST  (Update Admin Role ID)- invalid Admin id - valid endpoint

	@When("Admin sends HTTPS Request with invalid AdminId and valid {string} endpoint")
	public void admin_sends_https_request_with_invalid_adminid_and_valid_endpoint(String method) {

		/*if(method.equalsIgnoreCase("POST"))
			userResponse = reqspec.pathParam("userId", invalid_Id).body(userreqBodyAll)
			.when().put(baseURL+"/users/roleId/{userId}");*/
		if(method.equalsIgnoreCase("PUT - User"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/{userId}");
		else if(method.equalsIgnoreCase("DELETE - User"))
			resBody = reqspec.pathParam("userId", invalid_Id).when().delete(baseURL+"/users/{userId}");
		else if(method.equalsIgnoreCase("GET - Admin with filters"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().get(baseURL+"v2/users");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role ID"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/roleId/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin role status by Admin ID"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/roleStatus/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role Program Batch status"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/roleProgramBatchStatus/{userId}");
		else if (method.equalsIgnoreCase("PUT - Update Admin login status"))
			resBody = reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/userLogin/{userId}");

	}

	@When("Admin sends HTTPS Request with valid endpoint for AdminByID")
	public void admin_sends_https_request_with_valid_endpoint_for_admin_by_id() {
		resBody=reqspec.when().get(get_admin_by_userId);
		System.out.println(resBody);
		JsonPath jsonres = resBody.jsonPath();
		roleId = jsonres.get("roleId");
		configreader.writingdata("roleId",roleId);	

	}
	// PUT REQUEST  (Update Admin Role ID)- run the Update role id request twice

	// PUT Request (Update Admin Role ID)- Unauthorized 
	@When("Admin sends HTTPS {string} Request with endpoint unauthorized")
	public void admin_sends_https_request_with_endpoint_unauthorized(String method) {

		if(method.equalsIgnoreCase("GET - Admin with roles"))
			resBody = noauth_req_post.when().get(baseURL+"/users/roleStatus");
		else if(method.equalsIgnoreCase("GET - Admin with filters"))
			resBody = noauth_req_post.when().get(baseURL+"v2/users");
		else if(method.equalsIgnoreCase("PUT - User"))
			resBody = noauth_req_post.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/{userId}");
		else if(method.equalsIgnoreCase("DELETE - User"))
			resBody = noauth_req_post.pathParam("userId", userId).when().delete(baseURL+"/users/{userId}");		
		else if(method.equalsIgnoreCase("PUT - Update Admin Role ID"))
			resBody = noauth_req_post.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleId/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin role status by Admin ID"))
			resBody = noauth_req_post.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleStatus/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role Program Batch status"))
			resBody = noauth_req_post.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/roleProgramBatchStatus/{userId}");
		else if (method.equalsIgnoreCase("PUT - Update Admin login status"))
			resBody = noauth_req_post.pathParam("userId", userId).body(userReqbody).when().put(baseURL+"/users/userLogin/{userId}");

	}

	@Then("Admin receives status {int} with Unauthorized Message")
	public void admin_receives_status_with_unauthorized_message(int unautherrmsg) {
		//System.out.println(resBody.statusCode());
		//Assert.assertEquals(resBody.statusCode(), unautherrmsg);
		cv.statusValidations(userResponse, unautherrmsg);
		cv.headervalidations(userResponse);
		//need to do message validation
	}

	//PUT Request --NEGATIVE SCENARIOS
	@Given("Admin creates Update User Role Status PUT Request with in valid data in request body")
	public void admin_creates_update_user_role_status_put_request_with_in_valid_data_in_request_body() throws InvalidFormatException, IOException {
		//		userrolestatuspayload = userReqbody.returnupdateInvalidUserRoleStatus("UserModulePUT_UserRoleStatus");
		//		userBody=userReqbody.convertJsonToString(userrolestatuspayload);
		//		System.out.println(userreqBodyAll);
	}

	//PUT Request - invalid Admin Id Valid end point

	@When("Admin sends HTTPS Request with Update User Role Status invalid endpoint")
	public void admin_sends_https_request_with_update_user_role_status_invalid_endpoint() {

		resBody= reqspec.pathParam("userId", invalid_Id).body(userReqbody).when().put(baseURL+"/users/roleStatus/{userId}");
		resBody.prettyPrint();
	}

	//PUT Request - invalid end point
	@When("Admin sends HTTPS Request with invalid {string} endpoint")
	public void admin_sends_https_request_with_invalid_endpoint(String method) {
		//resBody=reqspec.when().get(invalid_endpoint);    //invalid endpoint from reusable variables

		if(method.equalsIgnoreCase("GET - Admin with roles"))
			resBody = reqspec.when().get(baseURL+"/users/roleStatus");
		else if(method.equalsIgnoreCase("GET - Admin with filters"))
			resBody = reqspec.when().get(baseURL+"v2/users");
		else if(method.equalsIgnoreCase("PUT - User"))
			resBody = reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+invalid_endpoint);
		else if(method.equalsIgnoreCase("DELETE - User"))
			resBody = reqspec.pathParam("userId", userId).when().delete(baseURL+invalid_endpoint);		
		else if(method.equalsIgnoreCase("PUT - Update Admin Role ID"))
			resBody = reqspec.body(userReqbody).when().put(baseURL+invalid_endpoint);
		else if(method.equalsIgnoreCase("PUT - Update Admin role status by Admin ID"))
			resBody = reqspec.pathParam("userId", userId).when().put(baseURL+invalid_endpoint);
		else if(method.equalsIgnoreCase("PUT - Update Admin Role Program Batch status"))
			resBody = reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+invalid_endpoint);
		else if (method.equalsIgnoreCase("PUT - Update Admin login status"))
			resBody = reqspec.pathParam("userId", userId).body(userReqbody).when().put(baseURL+invalid_endpoint);
	}



	//Delete User/AdminID with valid User/Admin ID

	@When("Admin sends HTTPS request with delete endpoiint")
	public void admin_sends_https_request_with_delete_endpoiint() {
		String userId = configreader.getUserIdWithAAllField();
		System.out.println(userId);		
		resBody= reqspec.pathParam("userId", userId).when().delete(baseURL+"/users/{userId}");
		resBody.prettyPrint();
	}

}