package stepDefinition;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.CommonValidation;
import utilities.ConfigReader;
import utilities.ExcelReader1;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class userLoginStepDefinition extends ReusableVariables{

	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	LoginRequestBody loginReqbody=new  LoginRequestBody();
	ExcelReader1 read = new ExcelReader1();
	String reqBody;
	Response loginResponse;
	ConfigReader configreader=new ConfigReader();
    Properties prop =configreader.readingdata();

	//CommonValidation cv=new CommonValidation();

	@Given("Admin creates request with valid credentials")
	public void admin_creates_request_with_valid_credentials() throws IOException, InvalidFormatException {
		 
		 
		 List<Map<String, String>> hm=read.getData(path,"Login");
		reqBody = loginReqbody.createLoginRequest(hm);
		 
	}

	@When("Admin Admin calls Post Https method  with valid endpoint")
	public void admin_admin_calls_post_https_method_with() {
		System.out.println("Inside Response");
	   
	    	loginResponse= given().header("Content-Type","application/json").body(reqBody).when().post(baseURL+"/login");
	    }

@When("Admin calls Post Https method  with Invalid endpoint")
 public void admin_calls_post_https_method_with_invalid_endpoint() {
	
    	loginResponse= given().header("Content-Type","application/json").body(reqBody).when().post(baseURL+"/log");
    }
	

	@Then("Admin Admin receives {int} created with auto generated token")
	public void admin_admin_receives_created_with_auto_generated_token(Integer successcode) {
		System.out.println(loginResponse.statusCode());
	    Assert.assertEquals(loginResponse.statusCode(), successcode);
		JsonPath gettoken = loginResponse.jsonPath();
        String bearerToken = gettoken.get("token");
        //System.out.println("BearerToken - "+bearerToken);
        configreader.writingdata(bearerToken);
       System.out.println(prop.getProperty("bearer"));
       
	    
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