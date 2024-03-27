package StepDefinition;

import static io.restassured.RestAssured.given;

import Utilities.CommonValidation;
import Utilities.ReusableMethods;
import Utilities.ReusableVariables;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserModuleStepDefinition  {
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	CommonValidation cv=new CommonValidation();

	@Given("User Login to LMS Application and creates BearerToken")
	public void user_login_to_lms_applicationandcreatesBearerToken() {
		System.out.println("Bearer Token created : " +reuseMethods.returnToken());
		reuseVariables.authValue = "Bearer "+reuseMethods.returnToken();
		
		
	}

	@Given("User creates request for the LMS API endpoint with Authorization")
	public void user_creates_request_for_the_lms_api_endpoint_with_authorization() {
		System.out.println("Inside GetRoles");
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
