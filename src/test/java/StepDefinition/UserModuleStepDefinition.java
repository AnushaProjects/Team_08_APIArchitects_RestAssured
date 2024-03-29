package StepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import RequestBodyRaw.UserRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.CommonValidation;
import utilities.ConfigReader;
import utilities.ExcelREaderData;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class UserModuleStepDefinition extends ReusableVariables  {
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	UserRequestBody userReqbody=new UserRequestBody();
	ExcelREaderData read = new ExcelREaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	String userreqBody;
	Response userResponse;
	
	
	//Creating User Id
	
	@Given("Admin creates POST request with all mandatory fields")
	public void admin_creates_post_request_with_all_mandatory_fields() throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"UserModule");
		userreqBody = userReqbody.createUserRequest(hm);
	}

	@When("Admin sends HTTPS Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		System.out.println(userreqBody);
		System.out.println("Bearer " + prop.getProperty("bearer"));
		userResponse= given().header("Content-Type","application/json").header("Authorization","Bearer " + prop.getProperty("bearer")).body(userreqBody).when().post(baseURL+"/users/roleStatus");
		userResponse.prettyPrint();
	}

	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer created) {
		System.out.println(userResponse.statusCode());
		Assert.assertEquals(userResponse.statusCode(),created );
		
	}
	

	@Given("User creates request for the LMS API endpoint with Authorization")
	public void user_creates_request_for_the_lms_api_endpoint_with_authorization() {
		System.out.println("Inside GetRoles");
		reuseVariables.authValue = "Bearer "+prop.getProperty("bearer");
		System.out.println(reuseVariables.authValue);
		Response res=given().header("Authorization", reuseVariables.authValue).when().get(reuseVariables.baseURL+"/users/roles");
		System.out.println(res.asPrettyString());
		System.out.println(res.statusCode());
	}

	@When("User  sends HTTPS Request with GET All Roles endpoint")
	public void user_sends_https_request_with_get_all_roles_endpoint() {
	   
	}

	@Then("User receives status code {int} with response body for viewing an User by Role")
	public void user_receives_status_code_with_response_body_for_viewing_an_user_by_role(Integer int1) {
	   
	}
}
