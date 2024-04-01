
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
	 import io.restassured.response.Response;
import payLoad.ProgramPayload;
import utilities.ConfigReader;
import utilities.ConfigReader;
	//import utilities.ExcelREaderData;
import utilities.ExcelReader1;
import utilities.ReusableMethods;
	import utilities.ReusableVariables;
	


	public class ProgramStepDefinition extends ReusableVariables{
		ReusableMethods reuseMethods=new ReusableMethods();
		ReusableVariables reuseVariables=new ReusableVariables();
		ProgramRequestBody programreqBody=new ProgramRequestBody ();
		//RequestBody batchreqbody=new BatchRequestBody();
		ExcelReader1 read = new ExcelReader1();
		String programrequestBody;
		String batchrequestBody;
		 private static String programName;
		Response ProgramResponse;
		private static String programId;
	  //  private static String programName;
		ConfigReader configreader=new ConfigReader();
	    Properties prop =configreader.readingdata();

		
	    @Given("Admin creates POST program Request with valid data in request body")
	    public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException 
	    {
	    	List<Map<String, String>> hm=read.getData(path,"Program");
			 programrequestBody = programreqBody.createProgramRequest(hm); 
	    }

	    @When("Admin sends HTTPS Request with program endpoint")
	    public void admin_sends_https_request_with_program_endpoint()  
	    {
	    	System.out.println("Inside post program Response");
			System.out.println( "Bearer " + prop.getProperty("bearer"));
			System.out.println(programrequestBody);
	ProgramResponse= given().header("Content-Type","application/json").header("Authorization","Bearer " + prop.getProperty("bearer"))
	.body(programrequestBody).when().post(baseURL+"/saveprogram");

	   ProgramResponse.prettyPrint();
	   System.out.println("Printing the program id from response: "+ProgramResponse.path("programId"));
	  // System.out.println(programrequestBody.getProgramName());

	   Integer programIdFromResp = ProgramResponse.path("programId");
	   programId = programIdFromResp.toString();
	   
	   programName=ProgramResponse.path("programName");
	   System.out.println("Printing the program id after retrieving: "+programId);
	   System.out.println("Printing the program name after retrieving: "+programName);
  
	    }
	
	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer int1) 
	{
	    
	}
	@When("Admin sends HTTPS Request and request Body with endpoint without authorization")
	public void admin_sends_https_request_and_request_body_with_endpoint_without_authorization() {
	    
	
	ProgramResponse= given().header("Content-Type","application/json")
			.body(programrequestBody).when().post(baseURL+"/saveprogram");
	
	ProgramResponse.prettyPrint();
	}
	
	@When("Admin sends HTTPS Request and request Body with invalid endpoint")
	public void admin_sends_https_request_and_request_body_with_invalid_endpoint() 
	{
		ProgramResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(programrequestBody).when().post(baseURL+"/saveprogra");  
	}

	@Then("Admin receives {int} Bad Request Status")
	public void admin_receives_bad_request_status(Integer int1) {
	    

	}
	@Given("Admin creates POST program Request with invalid valid data in request body")
	public void admin_creates_post_program_request_with_invalid_valid_data_in_request_body() throws InvalidFormatException, IOException 
	{
		List<Map<String, String>> hm=read.getData(path,"Program");
	//	programrequestBody = programreqBody.createProgramRequestwithmissingmandatoryfields(hm,programId);
	}
	@When("Admin sends HTTPS Request and  missingvaluesinrequest Body with endpoint")
	public void admin_sends_https_request_and_missingvaluesinrequest_body_with_endpoint() 
	{
		ProgramResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(programrequestBody).when().post(baseURL+"/saveprograms");
		ProgramResponse.prettyPrint(); 
	}

	}
	    
	    
	    
		
