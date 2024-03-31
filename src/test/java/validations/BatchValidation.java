package validations;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.Matchers;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payLoad.BatchPayload;
import utilities.ExcelReader1;
import utilities.ReusableVariables;

public class BatchValidation extends ReusableVariables {
	//User user=new User();
		BatchPayload batchpayload=new BatchPayload();
		Response response;
		ExcelReader1 read = new ExcelReader1();
		
//		public void postResponse(Response response) throws IOException, ParseException {
//			
//		
//			String responseBody = response.getBody().asString();
//			JsonPath jsonPath = new JsonPath(responseBody);
//			int user_id = jsonPath.getInt("batchId");
//			
//			
//		}
		
		public void headervalidations(Response response) {
			
			
			Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
			Assert.assertEquals(response.getHeader("Server"), "Cowboy");
			Assert.assertEquals(response.getHeader("transfer-encoding"), "chunked");
			
		}
		
		
		public void schemavalidation(Response response) {
			response.then().assertThat()
		      .body(JsonSchemaValidator.
			 matchesJsonSchema(new File(".\\src\\test\\java\\schemaValidations\\BatchPostSchema.json")));
			}
		public void schemavalidationforupdate(Response response) {
			response.then().assertThat()
		      .body(JsonSchemaValidator.
			 matchesJsonSchema(new File(".\\src\\test\\java\\schemaValidations\\BatchPutSchema.json")));
			}
		
		
	public void datavalidation(Response response, BatchPayload batchrequestBody) throws IOException, InvalidFormatException {
		
	Assert.assertEquals(batchrequestBody.getBatchDescription(),response.jsonPath().get("batchDescription"));
	Assert.assertEquals(batchrequestBody.getBatchName(),response.jsonPath().get("batchName"));
	Assert.assertEquals(batchrequestBody.getBatchNoOfClasses(),String.valueOf(response.jsonPath().get("batchNoOfClasses")));
	Assert.assertEquals(batchrequestBody.getProgramId(),String.valueOf(response.jsonPath().get("programId")));
	Assert.assertEquals(batchrequestBody.getBatchStatus(),response.jsonPath().get("batchStatus"));

}
	public void datavalidation_for_update(Response response, BatchPayload batchrequestBody) throws IOException, InvalidFormatException {
		
		Assert.assertEquals(batchrequestBody.getBatchDescription(),response.jsonPath().get("batchDescription"));
		Assert.assertEquals(batchrequestBody.getBatchName(),response.jsonPath().get("batchName"));
		Assert.assertEquals(batchrequestBody.getBatchNoOfClasses(),String.valueOf(response.jsonPath().get("batchNoOfClasses")));
		Assert.assertEquals(batchrequestBody.getProgramId(),String.valueOf(response.jsonPath().get("programId")));
		Assert.assertEquals(batchrequestBody.getBatchStatus(),response.jsonPath().get("batchStatus"));
		Assert.assertEquals(batchrequestBody.getBatchId(),String.valueOf(response.jsonPath().get("batchId")));
		Assert.assertEquals(batchrequestBody.getProgramName(),response.jsonPath().get("programName"));

	}

	public void statusValidations(Response response,int statuscode) {
		System.out.println(response.getStatusCode());
		int codeofstatus=response.getStatusCode();
		Assert.assertEquals(codeofstatus,statuscode);
		
		
	}
	public void messageValidations(Response response,boolean successvalue) {
		//response.then().assertThat().body("success", Matchers.equalTo(successvalue));
		System.out.println(response.jsonPath().get("success"));
		Assert.assertSame(response.jsonPath().get("success"),successvalue);
       String responsemessage=(response.path("message"));
       responsemessage.isEmpty();
     Assert.assertFalse(responsemessage.isEmpty());

	}

}
