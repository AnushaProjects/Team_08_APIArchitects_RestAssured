package stepDefinition;

import static io.restassured.RestAssured.given;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.CommonValidation;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class userModuleStepDefinition {
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	CommonValidation cv=new CommonValidation();


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
