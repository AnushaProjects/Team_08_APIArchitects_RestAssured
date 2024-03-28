package StepDefinition;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.CommonValidation;
import utilities.ExcelREaderData;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class userLoginStepDefinition extends ReusableVariables{

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	LoginRequestBody loginReqbody=new  LoginRequestBody();
	ExcelREaderData read = new ExcelREaderData();
	String reqBody;
	Response loginResponse;
	String bearerToken;
	//CommonValidation cv=new CommonValidation();
	
	@Given("Admin creates request with {string} credentials")
	public void admin_creates_request_with_credentials(String condition) throws IOException {
		HashMap<String,String> hm=read.loginCred();
		reqBody = loginReqbody.createLoginRequest(hm,condition); 
	}

	
	
	@When("Admin Admin calls Post Https method  with {string}")
	public void admin_admin_calls_post_https_method_with(String endpoint) {
		//System.out.println("Inside Response");
	    if(endpoint.equalsIgnoreCase("valid endpoint")) {
	    	loginResponse= given().header("Content-Type","application/json").body(reqBody).when().post(baseURL+"/login");
	    }
	    
	    if(endpoint.equalsIgnoreCase("Invalid endpoint")){
	    	loginResponse= given().header("Content-Type","application/json").body(reqBody).when().post(baseURL+"/log");
	    }
	}
	

	@Then("Admin Admin receives {int} created with auto generated token")
	public void admin_admin_receives_created_with_auto_generated_token(Integer successcode) {
		System.out.println(loginResponse.statusCode());
	    Assert.assertEquals(loginResponse.statusCode(), successcode);
		JsonPath gettoken = loginResponse.jsonPath();
		bearerToken = gettoken.get("token");
        System.out.println("BearerToken - "+bearerToken);
       
	    
	}

	@Then("Admin Admin receives Admin receives {int} unauthorized")
	public void admin_admin_receives_admin_receives_unauthorized(Integer errorcode) {
		System.out.println(loginResponse.statusCode());
        Assert.assertEquals(loginResponse.statusCode(), errorcode);
	}
	
	@Then("Admin Admin receives Admin receives {int} BadRequest")
	public void admin_admin_receives_admin_receives_bad_request(Integer badrequest) {
		System.out.println(loginResponse.statusCode());
        Assert.assertEquals(loginResponse.statusCode(),badrequest );
	}

}
