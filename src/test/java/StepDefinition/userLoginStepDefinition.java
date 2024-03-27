package StepDefinition;


import static io.restassured.RestAssured.given;

import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.CommonValidation;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class userLoginStepDefinition extends ReusableVariables{

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	LoginRequestBody loginReqbody=new  LoginRequestBody();
	 
	//CommonValidation cv=new CommonValidation();

@Given("User Login to LMS Application")
public void user_login_to_lms_application() {
//	Response res = given().header("Content-Type","application/json").body(loginReqbody.loginBody).when().post(baseURL+"/login");
//	 System.out.println(res.asPrettyString());
}

@When("User enter valid credentials")
public void user_enter_valid_credentials() {
	
}

@Then("User receives status code {int} with response body with BearerToken")
public void user_receives_status_code_with_response_body_with_bearer_token(Integer int1) {
	System.out.println("Bearer Token created : " +reuseMethods.returnToken());
	reuseVariables.authValue = "Bearer "+reuseMethods.returnToken();
}
}
