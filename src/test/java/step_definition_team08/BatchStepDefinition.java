package step_definition_team08;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.ITestContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import payload_team08.BatchPayload;
import payload_team08.ProgramPayload;
import request_body_raw_team08.BatchRequestBody;
import request_body_raw_team08.LoginRequestBody;
import request_body_raw_team08.ProgramRequestBody;
import utilities_team08.BatchValidation;
import utilities_team08.CommonValidation;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;



public class BatchStepDefinition extends ReusableVariables{
	ReusableMethods reuseMethods=new ReusableMethods();
	ReusableVariables reuseVariables=new ReusableVariables();
	ProgramRequestBody programreqBody=new ProgramRequestBody ();
	CommonValidation cv=new CommonValidation();
	BatchRequestBody batchreqbody=new BatchRequestBody();
	ExcelReaderData read = new ExcelReaderData();
	ProgramPayload progpay=new ProgramPayload();
	BatchValidation bv=new BatchValidation();
ReusableVariables rv=new ReusableVariables();
ReusableMethods rm=new ReusableMethods();
BatchPayload bp;

ConfigReader cr =new ConfigReader();
Response response;


	ProgramPayload programrequestBody;
	BatchPayload batchrequestBody;
	BatchPayload batchUpdateRequestBody;
	Response ProgramResponse;
	Response BatchResponse;
	ConfigReader configreader=new ConfigReader();
    Properties prop =configreader.readingdata();
    //private static String programId;
    //private static String batchId;
    static String batchName;
    private static String programName;
    JSONObject batchBody;
    
//POST BATCH MODULE
//   //Program Module 
//	@Given("Admin creates POST program Request  with valid data in request body")
//	public void admin_creates_post_program_request_with_valid_data_in_request_body() throws InvalidFormatException, IOException {
//		 LoggerLoad.info("Admin creates POST program Request  with valid data in request body");
//		List<Map<String, String>> hm=read.getData(path,"Program");
//		 programrequestBody = programreqBody.createProgramRequest(hm);	}
//
//	@When("Admin sends HTTPS Request with program endpoint")
//	public void admin_sends_https_request_with_program_endpoint() {
//		 LoggerLoad.info("Admin sends HTTPS Request with program endpoint");
//
//		System.out.println("Inside post program Response");
//		System.out.println( "Bearer " + prop.getProperty("bearer"));
//		System.out.println(programrequestBody);
//        ProgramResponse= given().header("Content-Type","application/json").header("Authorization","Bearer " + prop.getProperty("bearer"))
//        .body(programrequestBody).when().post(baseURL+reuseVariables.Programpostendpoint);
//
//   ProgramResponse.prettyPrint();
//   System.out.println("Printing the program id from response: "+ProgramResponse.path("programId"));
//   System.out.println(programrequestBody.getProgramName());
//
//   Integer programIdFromResp = ProgramResponse.path("programId");
//   rv.programId = programIdFromResp.toString();
//   
//   programName=ProgramResponse.path("programName");
//   System.out.println("Printing the program id after retrieving: "+rv.programId);
//   System.out.println("Printing the program name after retrieving: "+programName);
//
//	}
//	@Then("Admin receives {int} Created Status with response body in program.")
//	public void admin_receives_created_status_with_response_body_in_program(Integer statuscode) {
//		 LoggerLoad.info("Admin receives {int} Created Status with response body in program.");
//
//		cv.statusValidations(ProgramResponse, statuscode, "status");
//	}

	@Given("Admin creates POST batch Request  with valid data in request body")
	public void admin_creates_post_request_with_valid_data_in_request_body_batch() throws InvalidFormatException, IOException {
		 LoggerLoad.info("Admin creates POST batch Request  with valid data in request body");
//		 bp=batchreqbody.returnUserPayloadbatch("Batch");
//		 batchrequestBody=batchreqbody.convertJsonToString(bp);
//			LoggerLoad.info("Converted ProgramRequestBody for Creating ProgramId to JSON Format " +userBody);
		 List<Map<String, String>> hm=read.getData(path,"Batch");
			System.out.println("Program id in batch request: "+prop.getProperty("program_Id_chaining"));  //prop.getProperty("program_Id_chaining")
			 batchrequestBody =batchreqbody.createBatchRequest(hm); //prop.getProperty("program_Id_chaining")
		 
		 System.out.println(batchrequestBody);
	}

	@When("Admin sends HTTPS batch Request with endpoint without authorization batch")
	public void admin_sends_https_request_with_endpoint_without_authorization_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint without authorization");
		BatchResponse= given().header("Content-Type","application/json")
				.body(batchrequestBody).when().post(baseURL+reuseVariables.createbatchendpoint);
		System.out.println(baseURL+reuseVariables.createbatchendpoint);
		BatchResponse.prettyPrint();
		}

	@Then("Admin receives {int} Unauthorized batch")
	public void admin_receives_unauthorized_batch(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} Unauthorized");
  	
		cv.statusValidations(BatchResponse, statuscode, "batchName");}
	
	
	@When("Admin sends HTTPS batch Request with endpoint batch")
	public void admin_sends_https_request_with_endpoint_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint");
		System.out.println("Indide positibe Post Batch"+prop.getProperty("program_Id_chaining"));
		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+reuseVariables.createbatchendpoint);
		System.out.println(baseURL+reuseVariables.createbatchendpoint);
		System.out.println("Checking Indide positibe Post Batch");
		Integer batchIdFromResp = BatchResponse.path("batchId");
		   rv.batchId = batchIdFromResp.toString();
		   batchName=BatchResponse.path("batchName");
		   System.out.println("Printing the batch id after retrieving: "+rv.batchId);
		   
	BatchResponse.prettyPrint();
	}
	
	
	@Then("Admin receives {int} Created Status with response body batch.")
	public void admin_receives_created_status_with_response_body_batch(Integer statuscode ) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} Created Status with response body.");
		
		cv.statusValidations(BatchResponse, statuscode, "batchName");
//    	bv.datavalidation(BatchResponse,batchrequestBody); //need chnag
//    	bv.schemavalidation(BatchResponse);
    	cv.headervalidations(BatchResponse);
    	
    	}
 @Given("Admin creates POST batch Request  with existing value in request body")
	public void admin_creates_post_request_with_existing_value_in_request_body_batch() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request  with existing value in request body");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		
		batchrequestBody = batchreqbody.createBatchRequestwithexistingdata(hm,prop.getProperty("program_Id_chaining"),batchName); //chng
	}
	@When("Admin sends HTTPS batch Request with endpoint with existing value in batchname batch")
	public void admin_sends_https_batch_request_with_endpoint_with_existing_value_in_batchname_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint with existing value in batchname");
	
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+reuseVariables.createbatchendpoint);
		
		BatchResponse.prettyPrint();
		}
		@Then("Admin receives {int} Bad Request Status with message and boolean success details batch")
	public void admin_receives_bad_request_status_with_message_and_boolean_success_details_batch(Integer statuscode) {
			
			LoggerLoad.info("Admin receives {int} Bad Request Status with message and boolean success details");
			
			cv.messageValidations(BatchResponse, false);
			cv.statusValidations(BatchResponse, statuscode, "batchName");}

	@Given("Admin creates POST batch Request  with invalid data in request body")
	public void admin_creates_post_request_with_invalid_data_in_request_body() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request  with invalid data in request body");

		List<Map<String, String>> hm=read.getData(path,"Batch");
	batchrequestBody = batchreqbody.createBatchRequestwithmissingmandatoryfields(hm,prop.getProperty("program_Id_chaining"));  
	}
	@When("Admin sends HTTPS batch Request with endpoint with missing mandatory fields batch")
	public void admin_sends_https_batch_request_with_endpoint_with_missing_mandatory_fields_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint with missing mandatory fields");

		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+reuseVariables.createbatchendpoint);
		BatchResponse.prettyPrint();
	}

	
	@When("Admin sends HTTPS batch Request with invalid endpoint batch")
	public void admin_sends_https_request_with_invalid_endpoint_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with invalid endpoint");
		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+reuseVariables.batchinvalidendpoint);
	}

	@Then("Admin receives {int} not found Status batch")
	public void admin_receives_not_found_status_batch(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} not found  Status");
		
		cv.statusValidations(BatchResponse, statuscode, "batchName");
    	}
 
	
	@Given("Admin creates POST batch Request with missing additional fields")
	public void admin_creates_post_request_with_missing_additional_fields() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request with missing additional fields");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
			batchrequestBody = batchreqbody.createBatchRequestwithmissingadditionalfields(hm,prop.getProperty("program_Id_chaining"));  
	}
	@Then("Admin receives {int} Created Status with response body for missing additional fields batch.")
	public void admin_receives_created_status_with_response_body_for_missing_additional_fields_batch(Integer statuscode) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} Created Status with response body for missing additional fields."); 
		
		cv.statusValidations(BatchResponse, statuscode, "batchName");
//		bv.datavalidation(BatchResponse, batchrequestBody); // need chng
	}

	@When("Admin sends HTTPS batch Request with endpoint and invalid data batch")
	public void admin_sends_https_batch_request_with_endpoint_and_invalid_data_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with endpoint and invalid data");
		
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchrequestBody).when().post(baseURL+reuseVariables.createbatchendpoint);
		}
	@Given("Admin creates POST batch Request with inactive program id")
	public void admin_creates_post_request_with_inactive_program_id() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates POST batch Request with inactive program id");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		batchrequestBody = batchreqbody.createBatchRequestwithinactiveprogramid(hm,programId);   
	}
	
	
	
	
	
	
	//PUT BATCH MODULE
	@Given("Admin creates PUT batch Request with valid BatchId and Data")
	public void admin_creates_put_batch_request_with_valid_batch_id_and_data() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with valid BatchId and Data");
		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+prop.getProperty("program_name_chaining"));
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest(hm,prop.getProperty("program_Id_chaining"),batchId,prop.getProperty("program_name_chaining"));   
	}
	@When("Admin sends HTTPS batch Request with update endpoint with no authorization batch")
	public void admin_sends_https_batch_request_with_update_endpoint_with_no_authorization_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint with no authorization");
		
		System.out.println("Batch requrest before response: "+batchUpdateRequestBody);
		BatchResponse= given().header("Content-Type","application/json")
				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+reuseVariables.updatevalidendpoint);
		BatchResponse.prettyPrint();
	}
	@When("Admin sends HTTPS batch Request with update endpoint batch")
	public void admin_sends_https_batch_request_with_update_endpoint_batch() {
		LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint");
		
		System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
		BatchResponse= given().header("Content-Type","application/json").
				header("Authorization","Bearer " + prop.getProperty("bearer"))
				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+reuseVariables.updatevalidendpoint);
		BatchResponse.prettyPrint();
	}

	@Then("Admin receives {int} OK Status with updated value in response body batch.")
	public void admin_receives_ok_status_with_updated_value_in_response_body_batch(Integer statuscode) throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin receives {int} OK Status with updated value in response body.");
		
//		bv.datavalidation_for_update(BatchResponse, batchUpdateRequestBody);
	    cv.headervalidations(BatchResponse);
//	    bv.schemavalidationforupdate(BatchResponse);
	    cv.statusValidations(BatchResponse, statuscode, "batchName");
	}
	
	
	

	@Given("Admin creates PUT batch Request with invalid BatchId and valid Data")
	public void admin_creates_put_batch_request_with_invalid_batch_id_and_valid_data() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with invalid BatchId and valid Data");

		
		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+programName);
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_invalidbatch_id(hm,prop.getProperty("program_Id_chaining"),batchId,prop.getProperty("program_name_chaining"));   
	
	}

	@Then("Admin receives {int} Not Found Status with message and boolean success details batch")
	public void admin_receives_not_found_status_with_message_and_boolean_success_details_batch(Integer statuscode) {
		LoggerLoad.info("Admin receives {int} Not Found Status with message and boolean success details");
System.out.println(BatchResponse);
		
cv.statusValidations(BatchResponse, statuscode, "batchName");
	   cv.messageValidations(BatchResponse, false);
	}
	@Given("Admin creates PUT batch Request with valid batch Id and missing mandatory fields")
	public void admin_creates_put_batch_request_with_valid_batch_id_and_missing_mandatory_fields() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with valid batch Id and missing mandatory fields");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+prop.getProperty("program_name_chaining"));
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_withmissingdata_validbatch_id(hm);   
		
	}

@When("Admin sends HTTPS batch Request with update endpoint and update batchid batch")
public void admin_sends_https_batch_request_with_update_endpoint_and_update_batchid_batch() {
	LoggerLoad.info("Admin sends HTTPS batch Request with update endpoint and update batchid");

	
	System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
	String batchIdUpdate = batchUpdateRequestBody.getBatchId();
	BatchResponse= given().header("Content-Type","application/json").
			header("Authorization","Bearer " + prop.getProperty("bearer"))
			.body(batchUpdateRequestBody).pathParam("batchId",batchIdUpdate).when().put(baseURL+reuseVariables.updatevalidendpoint);
	BatchResponse.prettyPrint();

}
      @Given("Admin creates PUT batch Request with invalid data")
 public void admin_creates_put_batch_request_with_invalid_data1() throws InvalidFormatException, IOException {
		LoggerLoad.info("Admin creates PUT batch Request with invalid data");

		List<Map<String, String>> hm=read.getData(path,"Batch");
		System.out.println("Program Name before calling update: "+prop.getProperty("program_name_chaining"));
		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequestwith_invalid_data(hm,prop.getProperty("program_Id_chaining"),batchId,prop.getProperty("program_name_chaining"));   
	}
     
      @When("Admin sends HTTPS PUT batch Request with invalid endpoint batch")
      public void admin_sends_https_put_batch_request_with_invalid_endpoint_batch() {
  		
   LoggerLoad.info("Admin sends HTTPS PUT batch Request with invalid endpoint");
  
    	  System.out.println("Batch requrest before response: "+batchUpdateRequestBody.getBatchId());
  		BatchResponse= given().header("Content-Type","application/json").
  				header("Authorization","Bearer " + prop.getProperty("bearer"))
  				.body(batchUpdateRequestBody).pathParam("batchId",batchId).when().put(baseURL+reuseVariables.Invalidendpointupdate);
  		BatchResponse.prettyPrint();   
      }
      @Given("Admin creates PUT batch Request with Valid batch Id and delete programId field")
      public void admin_creates_put_batch_request_with_valid_batch_id_and_delete_program_id_field() throws InvalidFormatException, IOException {
    	  
    	   LoggerLoad.info("Admin creates PUT batch Request with Valid batch Id and delete programId field");
  
    	  List<Map<String, String>> hm=read.getData(path,"Batch");
  		System.out.println("Program Name before calling update: "+programName);
  		batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_deleted_programidfield(hm,prop.getProperty("program_Id_chaining"),batchId,prop.getProperty("program_name_chaining"));   
  	  
      }
      @Given("Admin creates PUT batch Request with deleted batch Id")
      public void admin_creates_put_batch_request_with_deleted_batch_id() throws InvalidFormatException, IOException {
   	   
    	  LoggerLoad.info("Admin creates PUT batch Request with deleted batch Id");
 
    	  List<Map<String, String>> hm=read.getData(path,"Batch");
    	  System.out.println("Program Name before calling update: "+prop.getProperty("program_name_chaining"));
    	  batchUpdateRequestBody = batchreqbody.UpdateBatchputRequest_with_deleted_batchid(hm,prop.getProperty("program_Id_chaining"),batchId,prop.getProperty("program_name_chaining"));   
      }
      
      @Then("Admin receives {int} Ok status with message batch")
      public void admin_receives_ok_status_with_message(Integer statuscode) throws InvalidFormatException, IOException {
    	  LoggerLoad.info("Admin receives {int} Ok status with message");
  
//    	  bv.datavalidation_for_update(BatchResponse,batchUpdateRequestBody);
          cv.headervalidations(BatchResponse);
          cv.statusValidations(BatchResponse, statuscode, "batchName");
          //bv.schemavalidationforupdate(BatchResponse);
      }
//GET BATCH MODULE
      @Given("Admin creates batch GET Request")
  	public void admin_creates_batch_get_request_1() {
    	  LoggerLoad.info("Admin creates GET Request");
    	  RestAssured.baseURI= rv.baseURL;
  		rv.httpRequest= RestAssured.given().
  				header("Authorization","Bearer "+prop.getProperty("bearer"));
  	}
      @When("Admin sends HTTPS Request for GetAll_batch with valid endpoint batch")
  	public void admin_sends_https_request_for_get_all_batch_with_valid_endpoint_batch_1() {
    	  
    	  LoggerLoad.info("Admin sends HTTPS Request for GetAll_batch with valid endpoint");
    	  response=rv.httpRequest.when().get(rv.baseURL+rv.GetAllBatch);
  	}
      @Then("Admin receives {int} OK Status with response body batch.")
  	public void admin_receives_ok_status_with_response_body_batch_1(Integer StatusCode) {
  	    
    	  LoggerLoad.info("Admin receives {int} OK Status with response body.");
    	  response.then().statusCode(200);
  	    String RespoBody=response.getBody().asPrettyString();
  	    System.out.println();
  	    System.out.println("Response Body is: " + RespoBody);
  	    int statuscode= response.getStatusCode();
  	    System.out.println();
  	    System.out.println("Response status code is:"+statuscode);
  	    LoggerLoad.info("Success-"+ response.getStatusCode());
//  	  cv.statusValidations(BatchResponse, statuscode, "batchName");
  		
  	}


      @When("Admin sends HTTPS Request with endpoint batch")
  	public void admin_sends_https_request_with_endpoint_batch_1() {
    	  LoggerLoad.info("Admin sends HTTPS Request with endpoint");

          String searchfield="SAMPLE BATCH12";
  		rv.httpRequest= RestAssured.given().
  		header("Authorization","Bearer "+prop.getProperty("bearer"));
  		response=rv.httpRequest.given().queryParam("searchString", searchfield)
  				.when().get(rv.baseURL+rv.GetAllBatch);
  		
  	}
      @When("Admin sends HTTPS Request with invalid endpoint batch")
  	public void admin_sends_https_request_with_invalid_endpoint_batch_1() {
    	  LoggerLoad.info("Admin sends HTTPS Request with invalid endpoint");

  		rv.httpRequest= RestAssured.given().
  				header("Authorization","Bearer "+prop.getProperty("bearer"));
  				response=rv.httpRequest.get("xyz");
  	}
  	
  	@When("Admin sends HTTPS Request with invalid searchfieldID and valid endpoint batch")
  	public void admin_sends_https_request_with_invalid_searchfield_id_and_valid_endpoint_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with invalid searchfieldID and valid endpoint");
  		
  		String searchfield="aaaaaaaaaaaaaaaaaaaaa";
  		rv.httpRequest= RestAssured.given().
  		header("Authorization","Bearer "+prop.getProperty("bearer"));
  		response=rv.httpRequest.given().queryParam("searchString", searchfield)
  				.when().get(rv.baseURL+rv.GetAllBatch);
  	}

  	@When("Admin sends HTTPS Request for GetAll_batch with invalid endpoint batch")
  	public void admin_sends_https_request_for_get_all_batch_with_invalid_endpoint_batch_1() {
  	   
  		LoggerLoad.info("Admin sends HTTPS Request for GetAll_batch with invalid endpoint");
  		response=rv.httpRequest.when().get(rv.baseURL+"/Invalid");
  	}
  	
  	
  	@Then("Admin receives {int} status with error message Not Found batch.")
  	public void admin_receives_status_with_error_message_not_found_batch_1(Integer int1) {
  		LoggerLoad.info("Admin receives {int} status with error message Not Found.");

  		response.then().statusCode(404);
  	    String Respobody=response.body().asPrettyString();
  	    System.out.println("Invalid details:" + Respobody);
  	    LoggerLoad.info("Success-"+ response.getStatusCode());
  	}

  	@Then("Admin receives {int}  Status with error message unauthorized batch.")
  	public void admin_receives_status_with_error_message_unauthorized_batch_1(Integer int1) {
  		LoggerLoad.info("Admin receives {int}  Status with error message unauthorized.");

  		response.then().statusCode(401);
  	    String respobody=response.body().asPrettyString();
  	    System.out.println("Unautherized access"+respobody);
  	}

  	@Given("Admin creates batch GET Request without autherization")
  	public void admin_creates_batch_get_request_without_autherization_1() {
  		LoggerLoad.info("Admin creates GET Request without autherization");

  		RestAssured.baseURI= rv.baseURL;
  		rv.httpRequest=RestAssured.given().auth().none();
  		response=rv.httpRequest.get(rv.GetAllBatch);
  	}
  	
  	@When("Admin sends HTTPS Request with Invalid endpoint for BatchID batch")
  	public void admin_sends_https_request_with_invalid_endpoint_for_batch_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with Invalid endpoint for BatchID");

  		rv.httpRequest= RestAssured.given().
  				header("Authorization","Bearer "+prop.getProperty("bearer"));
  		String batchIdnum="8486";
  		response=rv.httpRequest
  				.given().pathParam("batchId",batchIdnum)
  				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchId}");
  	}
  	
  	@When("Admin sends HTTPS Request with endpoint for BatchID batch")
  	public void admin_sends_https_request_with_endpoint_for_batch_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for BatchID");
//  		Integer batchIdFromResp = BatchResponse.path("batchId");
//		   rv.batchId = batchIdFromResp.toString();
//		   batchName=BatchResponse.path("batchName");
//		   System.out.println("Printing the batch id after retrieving: "+rv.batchId);
  		//String batchIdnum="8765";
  		String batchIdnum=rv.batchId ;
  		response=rv.httpRequest
  				.given().pathParam("batchId",batchIdnum)
  				.when().get(rv.baseURL+rv.GetByBatchID+"{batchId}");
  		
  	}
  	@When("Admin sends HTTPS Request with endpoint for ProgramID batch")
	public void admin_sends_https_request_with_endpoint_for_program_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for ProgramID");

  		//String ProgID="16489";
  		String ProgID=rv.programId;
				  response=rv.httpRequest.pathParam("programId", ProgID)
				  .when().get(rv.baseURL+"/batches/program/{programId}");
	}	
  	@When("Admin sends HTTPS Request with invalid endpoint for ProgramID batch")
  	public void admin_sends_https_request_with_invalid_endpoint_for_program_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with invalid endpoint for ProgramID");

  		String ProgID="16765";
  				  response=rv.httpRequest.pathParam("programId", ProgID)
  				  .when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{programId}");
  	}

  	@When("Admin sends HTTPS Request with endpoint for deleted BatchID batch")
  	public void admin_sends_https_request_with_endpoint_for_deleted_batch_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for deleted BatchID");

  		String batchIdnum="8768";
  		response=rv.httpRequest
  				.given().pathParam("batchId",batchIdnum)
  				.when().get(rv.baseURL+rv.GetByBatchID+"{batchId}");
  		System.out.println(rv.baseURL+rv.GetByBatchID+"{batchId}");
  	}
  	
  	@Then("Admin receives {int} OK Status with  batchStatus field {string} in the response body batch.")
  	public void admin_receives_ok_status_with_batch_status_field_in_the_response_body_batch_1(Integer int1, String string) {
  		LoggerLoad.info("Admin receives {int} OK Status with  batchStatus field {string} in the response body.");

  		response.then().statusCode(200);
  	   String RespoBody=response.body().asPrettyString();
  	   System.out.println("Deleted ID Response body is:"+ RespoBody );
  	}

  	
  	@When("Admin sends HTTPS Request with endpoint for deleted ProgramID batch")
  	public void admin_sends_https_request_with_endpoint_for_deleted_program_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for deleted ProgramID");
	
  		String ProgID="16585";
  		  rv.httpRequest=RestAssured.given().
  				  header("Authorization","Bearer "+prop.getProperty("bearer"));
  				  response=rv.httpRequest.pathParam("programId", ProgID)
  				  .when().get(rv.baseURL+ rv.GetProgramID+"{programId}");
  	}

  	@When("Admin sends HTTPS Request with endpoint for invalid BatchID batch")
  	public void admin_sends_https_request_with_endpoint_for_invalid_batch_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for invalid BatchID");
String batchIdnum="abc";
  		response=rv.httpRequest
  				.given().pathParam("batchId",batchIdnum)
  				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchId}");
  	}
  	
  	@When("Admin sends HTTPS Request with endpoint for Invalid ProgramID batch")
  	public void admin_sends_https_request_with_endpoint_for_invalid_program_id_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for Invalid ProgramID");
	
  		String ProgID="xyz";
  		  rv.httpRequest=RestAssured.given().
  				  header("Authorization","Bearer "+prop.getProperty("bearer"));
  				  response=rv.httpRequest.pathParam("programId", ProgID)
  				  .when().get(rv.baseURL+ rv.GetProgramID+"{programId}");
  		   			  }
  	
  	@Then("Admin receives {int} Not Found Status with the message and boolean success details batch")
  	public void admin_receives_not_found_status_with_message_and_boolean_success_details_batch_1(Integer int1) {
  		LoggerLoad.info("Admin receives {int} Not Found Status with the message and boolean success details");
  
  		response.then().statusCode(404);
  	    String respo=response.getBody().asPrettyString();
  	    System.out.println("Response Body is:"+ respo);
  	}
  	
  	@When("Admin sends HTTPS Request with Invalid endpoint for Valid BatchName batch")
  	public void admin_sends_https_request_with_invalid_endpoint_for_valid_batch_name_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with Invalid endpoint for Valid BatchNam");	
  		rv.httpRequest= RestAssured.given().
  				header("Authorization","Bearer "+prop.getProperty("bearer"));
  		String BatchName="Api";
  		response=rv.httpRequest
  				.given().pathParam("batchName",BatchName)
  				.when().get(rv.baseURL+rv.Batch_Invalid_EndPoint+"{batchName}");
  	}
  	
  	@When("Admin sends HTTPS Request with endpoint for Valid BatchName batch")
  	public void admin_sends_https_request_with_endpoint_for_valid_batch_name_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for Valid BatchName");	

  		
  		//String BatchName="SAMPLE BATCH12";
  		String BatchName=batchName; 
  		response=rv.httpRequest
  				.given().pathParam("batchName",BatchName)
  				.when().get(rv.baseURL+ rv.GetBatchName +"{batchName}");
  	}
  	
  	
  	@When("Admin sends HTTPS Request with endpoint for InValid BatchName batch")
  	public void admin_sends_https_request_with_endpoint_for_in_valid_batch_name_batch_1() {
  		LoggerLoad.info("Admin sends HTTPS Request with endpoint for InValid BatchName");
  		
  		rv.httpRequest= RestAssured.given().
  				header("Authorization","Bearer "+prop.getProperty("bearer"));
  		String BatchName="12a";
  		response=rv.httpRequest
  				.given().pathParam("batchName",BatchName)
  				.when().get(rv.baseURL+rv.GetBatchName+"{batchName}");
  	}
  	
  	@When("Admin send request with valid program endpoint batch")
  	public void admin_send_request_with_valid_program_endpoint_batch_1() {
  		LoggerLoad.info("Admin send request with valid program endpoint");

  		response=rv.httpRequest.when().get(rv.baseURL+"/allPrograms");
  		
  	}

//DELETE BATCH MODULE
  	@Given("Admin creates batch DELETE Request")
	public void admin_creates_batch_delete_request_2() {
  		LoggerLoad.info("Admin send request with valid program endpoint");	
  		RestAssured.baseURI= rv.baseURL;
		rv.httpRequest= RestAssured.given().
				header("Authorization","Bearer "+prop.getProperty("bearer"));
	}

	@When("Admin sends HTTPS Request with valid BatchId and endpoint batch")
	public void admin_sends_https_request_with_valid_batch_id_and_endpoint_batch_2() {
  		LoggerLoad.info("Admin sends HTTPS Request with valid BatchId and endpoint");	

		//String DelID="8768";
		String DelID=rv.batchId;//8768 8765
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+prop.getProperty("bearer"));
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Then("Admin receives {int} Ok status with the message batch")
	public void admin_receives_ok_status_with_message_batch_2(Integer Statuscode) {
  		LoggerLoad.info("Admin receives {int} Ok status with the message");	

		response.then().statusCode(200);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	    cv.statusValidations(BatchResponse, statuscode, "batchName");
		//bv.headervalidations(response);
	    
	}

	@When("Admin sends HTTPS Request  with valid BatchId and invalid endpoint batch")
	public void admin_sends_https_request_with_valid_batch_id_and_invalid_endpoint_batch_2() {
  		LoggerLoad.info("Admin sends HTTPS Request  with valid BatchId and invalid endpoint");	
	
		String DelID="8765"; //8768
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+prop.getProperty("bearer"));
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.Batch_Invalid_EndPoint+"{id}");
	}

	@Then("Admin receives {int} not found batch")
	public void admin_receives_not_found_batch_2(Integer int1) {
  		LoggerLoad.info("Admin receives {int} not found");	
	
		response.then().statusCode(404);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	}

	@When("Admin sends HTTPS Request with invalid BatchId valid endpoint batch")
	public void admin_sends_https_request_with_invalid_batch_id_valid_endpoint_batch_2() {
  		LoggerLoad.info("Admin sends HTTPS Request with invalid BatchId valid endpoint");	

		String DelID="12"; //8768
		  rv.httpRequest=RestAssured.given().
				  header("Authorization","Bearer "+prop.getProperty("bearer"));
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Given("Admin creates batch DELETE Request with unauthorized access")
	public void admin_creates_batch_delete_request_with_unauthorized_access_2() {
  		LoggerLoad.info("Admin creates DELETE Request with unauthorized access");	

		rv.httpRequest=RestAssured.given();
		
	}
	
	@When("Admin sends HTTPS Request with valid BatchId and endpoint for unautherized batch")
	public void admin_sends_https_request_with_valid_batch_id_and_endpoint_for_unautherized_batch_2() {
  		LoggerLoad.info("Admin sends HTTPS Request with valid BatchId and endpoint for unautherized");	

		String DelID="8768"; //8768 8765
				  response=rv.httpRequest.pathParam("id", DelID)
				  .when().delete(rv.baseURL+rv.GetAllBatch+"/{id}");
	}

	@Then("Admin receives {int} Unauthorized access batch")
	public void admin_receives_unauthorized_status_batch_2(Integer int1) {
  		LoggerLoad.info("Admin receives {int} Unauthorized access");	
	
		response.then().statusCode(401);
	    String RespoBody=response.getBody().asPrettyString();
	    System.out.println();
	    System.out.println("Response Body is: " + RespoBody);
	    int statuscode= response.getStatusCode();
	    System.out.println();
	    System.out.println("Response status code is:"+statuscode);
	    LoggerLoad.info("Success-"+ response.getStatusCode());
	}

}