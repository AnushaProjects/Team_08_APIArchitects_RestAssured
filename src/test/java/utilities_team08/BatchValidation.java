package utilities_team08;

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
import payload_team08.BatchPayload;

import utilities_team08.ReusableVariables;

public class BatchValidation extends ReusableVariables {
	//User user=new User();
		BatchPayload batchpayload=new BatchPayload();
		Response response;
		ExcelReaderData read = new ExcelReaderData();
		
		
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
	public void Get_Request_ScheemaValidation(Response response) {
		response.then().assertThat()
	      .body(JsonSchemaValidator.
		 matchesJsonSchema(new File(".\\src\\test\\java\\schemaValidations\\Batch_Get_Request_ScheemaValidation.json")));
		}
	
	public void GetBatch_BatchNameScheemaValidation(Response response) {
		response.then().assertThat()
	      .body(JsonSchemaValidator.
		 matchesJsonSchema(new File(".\\src\\test\\resources\\schemaValidations\\GetBatch_BatchName.json")));
		}
	
	public void GetBatch_ProgramIDScheemaValidation(Response response) {
		response.then().assertThat()
	      .body(JsonSchemaValidator.
		 matchesJsonSchema(new File(".src\\test\\resources\\schemaValidations\\GetBatch_ProgramID.json")));
		}
	
	public void GetBatch_BatchIDScheemaValidation(Response response) {
		response.then().assertThat()
	      .body(JsonSchemaValidator.
		 matchesJsonSchema(new File(".\\src\\test\\resources\\schemaValidations\\GetBatch_BatchID.json")));
		}








	

}