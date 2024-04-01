package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.ITestContext;

import RequestBodyRaw.BatchRequestBody;
import RequestBodyRaw.LoginRequestBody;
import RequestBodyRaw.ProgramRequestBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import payLoad.BatchPayload;
import payLoad.ProgramPayload;
import utilities.CommonValidation;
import utilities.ConfigReader;
import utilities.ExcelReader1;
import utilities.LoggerLoad;
import utilities.ReusableMethods;
import utilities.ReusableVariables;
import validations.BatchValidation;


public class BatchStepDefinition extends ReusableVariables{
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	ProgramRequestBody programreqBody=new ProgramRequestBody ();
	CommonValidation cv=new CommonValidation();
	BatchRequestBody batchreqbody=new BatchRequestBody();
	ExcelReader1 read = new ExcelReader1();
	ProgramPayload progpay=new ProgramPayload();
	BatchValidation bv=new BatchValidation();
	ProgramPayload programrequestBody;
	BatchPayload batchrequestBody;
	BatchPayload batchUpdateRequestBody;
	Response ProgramResponse;
	Response BatchResponse;
	ConfigReader configreader=new ConfigReader();
    Properties prop =configreader.readingdata();
    private static String programId;
    private static String batchId;
    static String batchName;
    private static String programName;
    JSONObject batchBody;
	
	@Given("Admin creates POST program Request  with valid data in request body")
	public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		 LoggerLoad.info("Admin creates POST program Request  with valid data in request body");
		List<Map<String, String>> hm=read.getData(path,"Program");
		 programrequestBody = programreqBody.createProgramRequest(hm);	}

	@When("Admin sends HTTPS Request with program endpoint")
	public void admin_sends_https_request_with_program_endpoint() {
		 LoggerLoad.info("Admin sends HTTPS Request with program endpoint");

		System.out.println("Inside post program Response");
		System.out.println( "Bearer " + prop.getProperty("bearer"));
		System.out.println(programrequestBody);
ProgramResponse= given().header("Content-Type","application/json").header("Authorization","Bearer " + prop.getProperty("bearer"))
.body(programrequestBody).when().post(baseURL+"/saveprogram");

   ProgramResponse.prettyPrint();
   System.out.println("Printing the program id from response: "+ProgramResponse.path("programId"));
   System.out.println(programrequestBody.getProgramName());

   Integer programIdFromResp = ProgramResponse.path("programId");
   programId = programIdFromResp.toString();
   
   programName=ProgramResponse.path("programName");
   System.out.println("Printing the program id after retrieving: "+programId);
   System.out.println("Printing the program name after retrieving: "+programName);

	}
	@Then("Admin receives {int} Created Status with response body in program.")
	public void admin_receives_created_status_with_response_body_in_program(Integer statuscode) {
		 LoggerLoad.info("Admin receives {int} Created Status with response body in program.");

		cv.statusValidations(ProgramResponse, statuscode);
	}

	@Given("Admin creates POST batch Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
		 LoggerLoad.info("Admin creates POST batch Request  with valid data in request body");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program id in batch request: "+programId);
		 batchrequestBody =batchreqbody.createBatchRequest(hm,programId);
	}

	@When("Admin sends HTTPS batch Request with endpoint without authorization")
	public void admin_sends_https_request_with_endpoint_without_authorization() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint without authorization");
		BatchResponse= given().header("Content-Type","application/json")
				.body(batchrequestBody).when().post(baseURL+"/batches");
		
		BatchResponse.prettyPrint();
		}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} Unauthorized");
  	
		cv.statusValidations(BatchResponse,statuscode );}
	
	
	@When("Admin sends HTTPS batch Request with endpoint")
	public void admin_sends_https_request_with_endpoint() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint");
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+"/batches");
		Integer batchIdFromResp = BatchResponse.path("batchId");
		   batchId = batchIdFromResp.toString();
		   batchName=BatchResponse.path("batchName");
		   System.out.println("Printing the batch id after retrieving: "+batchId);
		   
	BatchResponse.prettyPrint();
	}
	
	
	@Then("Admin receives {int} Created Status with response body.")
	public void admin_receives_created_status_with_response_body(Integer statuscode ) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} Created Status with response body.");
		
		cv.statusValidations(BatchResponse,statuscode );
    	bv.datavalidation(BatchResponse,batchrequestBody);
    	bv.schemavalidation(BatchResponse);
    	cv.headervalidations(BatchResponse);
    	
    	}
 @Given("Admin creates POST batch Request  with existing value in request body")
	public void admin_creates_post_request_with_existing_value_in_request_body() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request  with existing value in request body");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		
		batchrequestBody = batchreqbody.createBatchRequestwithexistingdata(hm,programId,batchName);
	}
	@When("Admin sends HTTPS batch Request with endpoint with existing value in batchname")
	public void admin_sends_https_batch_request_with_endpoint_with_existing_value_in_batchname() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint with existing value in batchname");
	
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+"/batches");
		
		BatchResponse.prettyPrint();
		}
		@Then("Admin receives {int} Bad Request Status with message and boolean success details")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details(Integer statuscode) {
			
			LoggerLoad.info("Admin receives {int} Bad Request Status with message and boolean success details");
			
			cv.messageValidations(BatchResponse, false);
	    cv.statusValidations(BatchResponse, statuscode);}

	@Given("Admin creates POST batch Request  with invalid data in request body")
	public void admin_creates_post_request_with_invalid_data_in_request_body() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request  with invalid data in request body");

		List<Map<String, String>> hm=read.getData(path,"Batch");
	batchrequestBody = batchreqbody.createBatchRequestwithmissingmandatoryfields(hm,programId);
	}
	@When("Admin sends HTTPS batch Request with endpoint with missing mandatory fields")
	public void admin_sends_https_batch_request_with_endpoint_with_missing_mandatory_fields() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint with missing mandatory fields");

		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+"/batches");
		BatchResponse.prettyPrint();
	}

	
	@When("Admin sends HTTPS batch Request with invalid endpoint")
	public void admin_sends_https_request_with_invalid_endpoint() {
		LoggerLoad.info("Admin sends HTTPS batch Request with invalid endpoint");
		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+"/batche");
	}

	@Then("Admin receives {int} not found Status")
	public void admin_receives_not_found_status(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} not found  Status");
		
    	cv.statusValidations(BatchResponse,statuscode );
    	}
 
	
	@Given("Admin creates POST batch Request with missing additional fields")
	public void admin_creates_post_request_with_missing_additional_fields() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request with missing additional fields");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
			batchrequestBody = batchreqbody.createBatchRequestwithmissingadditionalfields(hm,programId);
	}
	@Then("Admin receives {int} Created Status with response body for missing additional fields.")
	public void admin_receives_created_status_with_response_body_for_missing_additional_fields(Integer statuscode) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} Created Status with response body for missing additional fields."); 
		
		cv.statusValidations(BatchResponse,statuscode );
		bv.datavalidation(BatchResponse, batchrequestBody);
	}

	@When("Admin sends HTTPS batch Request with endpoint and invalid data")
	public void admin_sends_https_batch_request_with_endpoint_and_invalid_data() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint and invalid data");
		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+"/batches");
		}
	@Given("Admin creates POST batch Request with inactive program id")
	public void admin_creates_post_request_with_inactive_program_id() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request with inactive program id");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		batchrequestBody = batchreqbody.createBatchRequestwithinactiveprogramid(hm,programId);  
	}
	
	@Given("Admin creates PUT batch Request with valid BatchId and Data")
	public void admin_creates_put_batch_request_with_valid_batch_id_and_data() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with valid BatchId and Data");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+programName);
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest(hm,programId,batchId,programName);   
	}
	@When("Admin sends HTTPS batch Request with update endpoint with no authorization")
	public void admin_sends_https_batch_request_with_update_endpoint_with_no_authorization() {
		LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint with no authorization");
		
		System.out.println("Batch requrest before response: "+batchUpdateRequestBody);
		BatchResponse= given().header("Content-Type","application/json")
				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+"/batches/{batchId}");
		BatchResponse.prettyPrint();
	}
	@When("Admin sends HTTPS batch Request with update endpoint")
	public void admin_sends_https_batch_request_with_update_endpoint() {
		LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint");
		
		System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+"/batches/{batchId}");
		BatchResponse.prettyPrint();
	}

	@Then("Admin receives {int} OK Status with updated value in response body.")
	public void admin_receives_ok_status_with_updated_value_in_response_body(Integer statuscode) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} OK Status with updated value in response body.");
		
		bv.datavalidation_for_update(BatchResponse, batchUpdateRequestBody);
	    cv.headervalidations(BatchResponse);
	    bv.schemavalidationforupdate(BatchResponse);
	    cv.statusValidations(BatchResponse,statuscode );
	}
	
	
	

	@Given("Admin creates PUT batch Request with invalid BatchId and valid Data")
	public void admin_creates_put_batch_request_with_invalid_batch_id_and_valid_data() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with invalid BatchId and valid Data");

		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+programName);
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_invalidbatch_id(hm,programId,batchId,programName);   
	
	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} Not Found Status with message and boolean success details");

		
		cv.statusValidations(BatchResponse, statuscode);
	   cv.messageValidations(BatchResponse, false);
	}
	@Given("Admin creates PUT batch Request with valid batch Id and missing mandatory fields")
	public void admin_creates_put_batch_request_with_valid_batch_id_and_missing_mandatory_fields() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with valid batch Id and missing mandatory fields");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+programName);
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_withmissingdata_validbatch_id(hm);   
		
	}

@When("Admin sends HTTPS batch Request with update endpoint and update batchid")
public void admin_sends_https_batch_request_with_update_endpoint_and_update_batchid() {
	LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint and update batchid");

	
	System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
	String batchIdUpdate = batchUpdateRequestBody.getBatchId();
	BatchResponse= given().header("Content-Type","application/json").
			header("Authorization","Bearer " + prop.getProperty("bearer"))
			.body(batchUpdateRequestBody).pathParam("batchId",batchIdUpdate).when().put(baseURL+"/batches/{batchId}");
	BatchResponse.prettyPrint();

}
      @Given("Admin creates PUT batch Request with invalid data")
 public void admin_creates_put_batch_request_with_invalid_data1() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with invalid data");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+programName);
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequestwith_invalid_data(hm,programId,batchId,programName);   
	}
     
      @When("Admin sends HTTPS PUT batch Request with invalid endpoint")
      public void admin_sends_https_put_batch_request_with_invalid_endpoint() {
  		
   LoggerLoad.info("Admin sends HTTPS PUT batch Request with invalid endpoint");
  
    	  System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
  		BatchResponse= given().header("Content-Type","application/json").
  				header("Authorization","Bearer " + prop.getProperty("bearer"))
  				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+"/batch/{batchId}");
  		BatchResponse.prettyPrint();   
      }
      @Given("Admin creates PUT batch Request with Valid batch Id and delete programId field")
      public void admin_creates_put_batch_request_with_valid_batch_id_and_delete_program_id_field() throws InvalidFormatException, IOException {
    	  
    	   LoggerLoad.info("Admin creates PUT batch Request with Valid batch Id and delete programId field");
  
    	  List<Map<String, String>> hm=read.getData(path,"Batch");
  		System.out.println("Program Name before calling update: "+programName);
  		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_deleted_programidfield(hm,programId,batchId,programName);   
  	  
      }
      @Given("Admin creates PUT batch Request with deleted batch Id")
      public void admin_creates_put_batch_request_with_deleted_batch_id() throws InvalidFormatException, IOException {
   	   
    	  LoggerLoad.info("Admin creates PUT batch Request with deleted batch Id");
 
    	  List<Map<String, String>> hm=read.getData(path,"Batch");
    	  System.out.println("Program Name before calling update: "+programName);
    	  batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_with_deleted_batchid(hm,programId,batchId,programName);   
      }
      
      @Then("Admin receives {int} Ok status with message")
      public void admin_receives_ok_status_with_message(Integer statuscode) throws InvalidFormatException, IOException {
    	  LoggerLoad.info("Admin receives {int} Ok status with message");
  
    	  bv.datavalidation_for_update(BatchResponse,batchUpdateRequestBody);
          cv.headervalidations(BatchResponse);
          cv.statusValidations(BatchResponse, statuscode);
          bv.schemavalidationforupdate(BatchResponse);
      }





}
