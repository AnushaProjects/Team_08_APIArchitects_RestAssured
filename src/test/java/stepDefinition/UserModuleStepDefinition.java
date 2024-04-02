//package stepDefinition;
//
//import static io.restassured.RestAssured.given;
//
//import static org.junit.Assert.assertEquals;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.testng.Assert;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.response.Response;
//import requestBodyRaw.UserRequestBody;
//import utilities.CommonValidation;
//import utilities.ConfigReader;
//import utilities.ExcelReader;
//import utilities.ReusableMethods;
//import utilities.ReusableVariables;
//
//
//public class UserModuleStepDefinition extends ReusableVariables  {
//	ReusableMethods reuseMethods=new ReusableMethods();
//	ReusableVariables reuseVariables=new ReusableVariables();
//	UserRequestBody userReqbody=new UserRequestBody();
//	ExcelReader read = new ExcelReader();
//	ConfigReader configreader=new ConfigReader();
//	Properties prop =configreader.readingdata();
//	CommonValidation cv = new CommonValidation();
//	String userreqBody;
//	Response userGetResponse;
//	Response userResponse;
//
//	@Given("User creates request for the LMS API endpoint with {string}")
//	public void user_creates_request_for_the_lms_api_endpoint_with(String test) {
//
//	}
///*
//	@When("User  sends HTTPS Request with {string}")
//	public void user_sends_https_request_with(String endpoint) {
//		if(endpoint.equalsIgnoreCase("valid endpoint")) {
//			userGetResponse= given().header("Content-Type","application/json")
//					.header("Authorization","Bearer " + prop.getProperty("bearer"))
//					.when().get(baseURL+"/users/roles");
//			System.out.println(userGetResponse.asPrettyString());
//		}
//
//		if(endpoint.equalsIgnoreCase("invalid endpoint")){
//			userGetResponse = given().header("Content-Type","application/json")
//					.header("Authorization","Bearer " + prop.getProperty("bearer"))
//					.when().get(baseURL+"/users/rolers");
//			System.out.println(userGetResponse.asPrettyString());
//		}
//	}
//
//	@Then("Admin receives {int} OK Status with response body")
//	public void admin_receives_ok_status_with_response_body(int statuscode) {
//		Assert.assertEquals(userGetResponse.statusCode(), statuscode);
//		
//	}
//*/
//	@Then("Admin receives status {int} with Not Found error message")
//	public void admin_receives_status_with_not_found_error_message(int errorstatuscode) {
//
//		System.out.println(userGetResponse.statusCode());
//		System.out.println(userGetResponse.asPrettyString());
//		Assert.assertEquals(userGetResponse.statusCode(), errorstatuscode);
//	}	
//
//
//	@Given("User creates request for the LMS API endpoint with invalid credentials")
//	public void user_creates_request_for_the_lms_api_endpoint_with_invalid_credentials() {
//		userGetResponse= given().when().get(baseURL+"/users/roles");
//	}
//
//	@Then("Admin receives status {int} with Unauthorized message")
//	public void admin_receives_status_with_unauthorized_message(int errorstatuscode) {
//		System.out.println(userGetResponse.asPrettyString());
//		Assert.assertEquals(userGetResponse.statusCode(), errorstatuscode);
//	}
//
//	//Get Admin with filters
//
//	@Given("Admin creates GET Request")
//	public void admin_creates_get_request() {
//		userGetResponse= given().when().get(baseURL+"/v2/users");
//	}
//
//
//	
//	}
//	@Then("Admin receives {int} OK with response body")
//	public void admin_receives_ok_with_response_body(int errorstatuscode) {
//		System.out.println(userGetResponse.asPrettyString());
//		Assert.assertEquals(userGetResponse.statusCode(), errorstatuscode);
//	}
//
//}