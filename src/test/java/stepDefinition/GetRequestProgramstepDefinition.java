package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import requestBody.ProgramRequestBody;
import requestBody.LoginRequestBody;
import requestBody.ProgramRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;
import utilities.ConfigReader;
	//import utilities.ExcelREaderData;
import utilities.ExcelReader1;
import utilities.ReusableMethods;
import utilities.ReusableVariables;
	

public class GetRequestProgramstepDefinition extends ReusableVariables{
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	ProgramRequestBody programreqBody=new ProgramRequestBody ();
	//RequestBody batchreqbody=new BatchRequestBody();
	ExcelReader1 read = new ExcelReader1();
	String programrequestBody;
	String batchrequestBody;
	Response ProgramResponse;
	private static String programId;
	ConfigReader configreader=new ConfigReader();
    Properties prop =configreader.readingdata();
    RequestSpecification req;
    @Given("Admin creates GET Request with valid Endpoint")
    public void admin_creates_get_request_with_valid_endpoint() throws InvalidFormatException, IOException 
    {
    	RestAssured.baseURI=reuseVariables.baseURL;
    	req=RestAssured.given().header("Authorization","Bearer " + prop.getProperty("bearer"));
    }

    @When("Admin sends HTTPS Request with endpoint")
    public void admin_sends_https_request_with_endpoint() 
    {
    	 
		  /*  System.out.println("Bearer " + prop.getProperty("bearer"));
		   // System.out.println(programrequestBody);
		    
		    ProgramResponse = req
		            
		           .header("Authorization", "Bearer " + prop.getProperty("bearer"))
		             .when()
		            .get(reuseVariables.baseURL +"/allPrograms"
		            		);

		    // Expecting response with status code 401 (Unauthorized)
		   // ProgramResponse.then().statusCode(401);

		    // Printing response body
		    ProgramResponse.prettyPrint();
	}*/
    	ProgramResponse=req.when().get(reuseVariables.baseURL+"/allPrograms");
    	// 
    }
        
    @Then("Admin receives {int} OK Status with response body")
    public void admin_receives_ok_status_with_response_body(Integer int1) 
    {
    	ProgramResponse.then().statusCode(200);
    	String Respo=ProgramResponse.body().asPrettyString(); 
    	System.out.println(Respo);
    	
    }
    @When("Admin sends HTTPS Request with invalid endpoint")
    public void admin_sends_https_request_with_invalid_endpoint() 
    {
    	System.out.println("Bearer " + prop.getProperty("bearer"));
		   // System.out.println(programrequestBody);
		    
		    ProgramResponse = given()
		            
		           .header("Authorization", "Bearer " + prop.getProperty("bearer"))
		             .when()
		            .get(reuseVariables.baseURL + "/allProgram"
		            		);

		    // Expecting response with status code 401 (Unauthorized)
		   // ProgramResponse.then().statusCode(401);

		    // Printing response body
		    ProgramResponse.prettyPrint();  
    }

    @Then("Admin receives {int} not found  Status with error message")
    public void admin_receives_not_found_status_with_error_message(Integer int1) {
        
    }
    @When("Admin sends HTTPS Request with invalid method")
    public void admin_sends_https_request_with_invalid_method() 
    {
    	System.out.println("Bearer " + prop.getProperty("bearer"));
		   // System.out.println(programrequestBody);
		    
		    ProgramResponse = given()
		            
		           .header("Authorization", "Bearer " + prop.getProperty("bearer"))
		             .when()
		            .post(reuseVariables.baseURL + "/allPrograms"
		            		);
		    ProgramResponse.prettyPrint();
        
    }
    @When("Admin sends HTTPS Request with endpoint without authorization")
    public void admin_sends_https_request_with_endpoint_without_authorization() 
    {
    	System.out.println("Bearer " + prop.getProperty("bearer"));
		   // System.out.println(programrequestBody);
		    
		    ProgramResponse = given()
		            
		           .header("Authorization", "Bearer " + prop.getProperty("O-auth"))
		             .when()
		            .post(reuseVariables.baseURL + "/allPrograms"
		            		);
		    ProgramResponse.prettyPrint();
        
    }

    @Then("Admin receives {int} Status with response body as Unauthorized")
    public void admin_receives_status_with_response_body_as_unauthorized(Integer int1) 
    {
        
    }
    
    @When("Admin sends HTTPS Request with a specific programid endpoint")
    public void admin_sends_https_request_with_a_specific_programid_endpoint() 
    {
    	System.out.println("Bearer " + prop.getProperty("bearer"));
		    System.out.println(ProgramResponse);
		    
		    ProgramResponse = given()
		            
		           .header("Authorization", "Bearer " + prop.getProperty("bearer"))
		        // .pathParam("programId",reuseVariables.programid)
		           .
		             when()
		            .get(reuseVariables.baseURL + "/programs/{programId}"
		            		
		            		);
		    
		    ProgramResponse.prettyPrint();
     
 }
    @Then("Admin receives {int} Unauthorized")
    public void admin_receives_unauthorized(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
       
    


    

}
