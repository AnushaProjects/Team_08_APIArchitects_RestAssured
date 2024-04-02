package stepDefinition;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payLoad.UserPayload;
import requestBodyRaw.UserRequestBody;
import utilities.CommonValidation;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class UserModuleNegSceStepDefinition extends ReusableMethods{
	
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	UserRequestBody userReqbody=new UserRequestBody();
	ExcelReader read = new ExcelReader();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	CommonValidation cv=new CommonValidation();
	UserPayload userpayload;
	//UserReqBdyUserLoginPayload userLogin;
	
	Response userResponse;
	Response resBody;
	JSONObject userBody;

	@When("Admin sends HTTPS {string} Request with endpoint Unauthorized")
	public void admin_sends_https_request_with_endpoint_unauthorized(String method) {
								
		if(method.equalsIgnoreCase("POST"))
			userResponse = noauth_req_post.when().post(baseURL+"/users/roleStatus");
		else if(method.equalsIgnoreCase("GET - Admin with roles"))
			userResponse = noauth_req_post.when().get(baseURL+"/users/roleStatus");
			//userResponse = noauth_req_post.when().get(baseURL+"/users/roleStatus");
		else if(method.equalsIgnoreCase("PUT"))
			userResponse = noauth_req_post.when().put(baseURL+"/users/{userId}");
		else if(method.equalsIgnoreCase("DELETE"))
			userResponse = noauth_req_post.when().delete(baseURL+"/users/{userId}");
		else if(method.equalsIgnoreCase("GET - Admin with filters"))
			userResponse = noauth_req_post.when().get(baseURL+"v2/users");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role ID"))
			userResponse = noauth_req_post.when().put(baseURL+"/users/roleId/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin role status by Admin ID"))
			userResponse = noauth_req_post.when().put(baseURL+"/users/roleStatus/{userId}");
		else if(method.equalsIgnoreCase("PUT - Update Admin Role Program Batch status"))
			userResponse = noauth_req_post.when().put(baseURL+"/users/roleProgramBatchStatus/{userId}");
		else if (method.equalsIgnoreCase("PUT - Update Admin login status"))
			userResponse = noauth_req_post.when().put(baseURL+"/users/userLogin/{userId}");
	}

	@Then("Admin receives status {int} with unauthorized message")
	public void admin_receives_status_with_unauthorized_message(int statuscode) {
		//LoggerLoad.info("check userId gets generated for Admin role with no auth");
		System.out.println(userResponse);
		cv.headervalidations(userResponse);
        cv.statusValidations(userResponse,statuscode);
        //LoggerLoad.info("UserId is not created");
	}
	
	
}
