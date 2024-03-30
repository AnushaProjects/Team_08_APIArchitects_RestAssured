package stepDefinition;

//ConfigReader configreader=new ConfigReader();
//Properties prop=configreader.readingdata();	
//ExcelReader1= new ExcelReader1();
//import org.testing.annotations.Test;
//import static org.hamcreast.Matchers.*;
//import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import payLoad.UserRoleProgramBatchMapPayload;
import utilities.ReusableMethods;
import utilities.ReusableVariables;


public class UserRoleProgramBatchMapStepDefinition extends UserRoleProgramBatchMapPayload{

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	LoginRequestBody loginReqbody=new LoginRequestBody();
	RequestSpecification httpRequest;
	Response res;
	String userId;
	String token = reuseMethods.returnToken();
	String invalidLoginCredetials="{"
			+ "  \"password\": \"invalid@2024\",\n"
			+ "  \"userLoginEmailId\": \"numpy@gmail.com\"\n"
			+ "}";
	//userRoleProgramBatchMap-with Authorization
	
	
	
	@Given("Admin creates GET Request to retrieve all Admins assigned to programs\\/batches")
	public void admin_creates_get_request_to_retrieve_all_admins_assigned_to_programs_batches() {
		//.contentType(ContentType.JSON)
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);
		res= httpRequest.when().get("userRoleProgramBatchMap");
		}

	@When("Admin sends HTTPS Request")
	public void admin_sends_https_request() {		
		System.out.println("Admin sends HTTP reques");	
		//res.then().log().all();
	}

	@Then("Admin receives {int} OK")
	public void admin_receives_ok(Integer int1) {		
		System.out.println(res.statusCode());
		Assert.assertEquals(res.statusCode(), int1);
	}

	//userRoleProgramBatchMap-with no Auth
	@Given("Admin creates GET Request to retrieve all Admins assigned to programs\\/batches with No Authorization")
	public void admin_creates_get_request_to_retrieve_all_admins_assigned_to_programs_batches_with_no_authorization() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().auth().none();
		res= httpRequest.when().get("/userRoleProgramBatchMap");
	}

	@Then("Admin receives status {int} with Unauthorized message")
	public void admin_receives_status_with_unauthorized_message(Integer int1) {
		System.out.println(res.statusCode());
		Assert.assertEquals(res.statusCode(), int1);
		 //String responsebody=res.body().asPrettyString();
		 //System.out.println("Unauthorized access"+responsebody);
	}
		
	
	//userRoleProgramBatchMap/{userId}- valid id
	@Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by AdminId")
	public void admin_creates_get_request_to_retrieve_admin_assigned_to_program_batch_by_admin_id() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer" +token);
		res= httpRequest.when().get("/userRoleProgramBatchMap/U01");
		}

	//userRoleProgramBatchMap/{userId}- invalid id
	@Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by invalid AdminID")
	public void admin_creates_get_request_to_retrieve_admin_assigned_to_program_batch_by_invalid_admin_id() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);
		res= httpRequest.when().get("/userRoleProgramBatchMap/M05");
	}

	@Then("Admin receives {int} Not Found")
	public void admin_receives(Integer int1) {
		System.out.println(res.statusCode());
		//res.then().statusCode(404);
		assertEquals(res.getStatusCode(), 404);
	}

	//userRoleProgramBatchMap/{userId}- with no Auth
	@Given("Admin creates GET Request to retrieve Admin assigned to Program\\/Batch by valid AdminID with No Authorization")
	public void admin_creates_get_request_to_retrieve_admin_assigned_to_program_batch_by_valid_admin_id_with_no_authorization() { 
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().auth().none();
		res= httpRequest.when().get("/userRoleProgramBatchMap/U02");
	}
	
	//userRoleProgramBatchMap/deleteAll/{userId}- valid id
	@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by AdminId")
	public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_admin_id() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);
		res= httpRequest.when().delete("/userRoleProgramBatchMap/deleteAll/U643");
	}
	
	//userRoleProgramBatchMap/deleteAll/{userId}- invalid id
	@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by invalid AdminId")
	public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_invalid_admin_id() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);
		res= httpRequest.when().delete("/userRoleProgramBatchMap/deleteAll/R67");
	}

	//userRoleProgramBatchMap/deleteAll/{userId}- with no Auth
	@Given("Admin creates DELETE Request to delete Admin assigned to program\\/batch by valid AdminId with No Auth")
	public void admin_creates_delete_request_to_delete_admin_assigned_to_program_batch_by_valid_admin_id_with_no_auth() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().auth().none();
		res= httpRequest.when().delete("/userRoleProgramBatchMap/deleteAll/U643");
	}


	
	
	//Post request- generate token with valid credential
	@Given("Admin creates request with valid credentials to generate token")
	public void admin_creates_request_with_valid_credentials_to_generate_token() {
		
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Content-Type","application/json").body(loginReqbody.loginBody).when();
        //System.out.println(res.asPrettyString());      		
	}

	@When("Admin calls Post Https method  with valid endpoint")
	public void admin_calls_post_https_method_with_valid_endpoint() {
		System.out.println("Post request- generate token with valid credential");
		res = httpRequest.post("/login");		
	}
	
	@Then("Admin receives {int} created with auto generated token")
	public void admin_receives_created_with_auto_generated_token(Integer int1) {
		//System.out.println("200 OK");				
		JsonPath gettoken = res.jsonPath();
        String token = gettoken.get("token");
        System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 200);
        //Assert.assertNotNull(token);
	}
	
	//Post request- generate token with invalid endpoint
	@Given("Admin creates request with valid credentials and invalid endpoint")
	public void admin_creates_request_with_valid_credentials_and_invalid_endpoint() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Content-Type","application/json").body(loginReqbody.loginBody).when();        
	}
	
	@When("Admin calls Post Https method  with invalid endpoint")
	public void admin_calls_post_https_method_with_invalid_endpoint() {
		System.out.println("Post request- generate token with invalid endpoint");
		res = httpRequest.post("/log");	
	}
	
	@Then("Admin receives {int} unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
		System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 401);
	}

	//Post request- generate token with invalid credential
	@Given("Admin creates request with invalid credentials and valid enpoint")
	public void admin_creates_request_with_invalid_credentials_and_valid_enpoint() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Content-Type","application/json").body(invalidLoginCredetials).when();
        //System.out.println(res.asPrettyString());  
	}

	@Then("Admin receives {int} Bad request")
	public void admin_receives_bad_request(Integer int1) {
		System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 401);
	}

		
	//Get logout- with valid Endpoint
	@Given("Admin creates request with valid endpoint for logout")
	public void admin_creates_request_with_valid_endpoint_for_logout() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);		
	}

	@When("Admin calls Get Https method with valid endpoint")
	public void admin_calls_get_https_method_with_valid_endpoint() {
		res = httpRequest.get("/logoutlms");	
	}

	@Then("Admin receives {int} ok  and response with {string}")
	public void admin_receives_ok_and_response_with(Integer int1, String string) {
		System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 200);
	}
	
	//Get logout- with invalid Endpoint
	@Given("Admin creates request with invalid endpoint for logout")
	public void admin_creates_request_with_invalid_endpoint_for_logout() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().header("Authorization", "Bearer " +token);		
	}
	
	@When("Admin calls Get Https method withinvalid endpoint")
	public void admin_calls_get_https_method_withinvalid_endpoint() {
		res = httpRequest.get("/logout");	
	}

	@Then("Admin receives {int} Not found")
	public void admin_receives_not_found(Integer int1) {
		System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 405);
	}

	//Get logout- with no Auth
	@Given("Admin creates request with no Auth for logout")
	public void admin_creates_request_with_no_auth_for_logout() {
		RestAssured.baseURI = reuseVariables.baseURL;
		httpRequest = RestAssured.given().auth().none();
	}

	@Then("Admin receives {int}  unauthorized")
	public void admin_receives_unauthorized1(Integer int1) {
	   res.then().statusCode(401);
	   String responsebody=res.body().asPrettyString();
	   System.out.println("Unauthorized access" +responsebody);
	}
	
	
}
