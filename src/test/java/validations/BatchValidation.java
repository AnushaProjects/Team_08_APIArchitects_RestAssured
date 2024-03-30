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
			 matchesJsonSchema(new File(".\\src\\test\\java\\Schema validations\\BatchSchema.json")));
			}
		
		
	public void datavalidation(Response response) throws IOException, InvalidFormatException {
		List<Map<String, String>> hm=read.getData(path,"Batch");
			//response.then().assertThat().body("batchDescription", Matchers.equalTo((hm.get(0).get("BatchDescription"))));
		System.out.println(batchpayload.getBatchDescription());
		Assert.assertEquals(response.path("batchDescription"), (hm.get(0).get("BatchDescription")));
		Assert.assertEquals(response.path("batchStatus"), (hm.get(0).get("BatchStatus")));
		Assert.assertEquals(response.path("batchNoOfClasses"), Integer.parseInt((hm.get(0).get("BatchNoOfClasses"))));
			//response.then().assertThat().body("batchStatus", Matchers.equalTo((hm.get(0).get("BatchStatus"))));
			//response.then().assertThat().body("batchNoOfClasses", Matchers.equalTo((hm.get(0).get("BatchNoOfClasses"))));


	                         
		}
	public void datavalidationformissingadditionalfields(Response response) throws InvalidFormatException, IOException {
		List<Map<String, String>> hm=read.getData(path,"Batch");

		Assert.assertEquals(response.path("batchDescription"), (hm.get(0).get("BatchDescriptionmissingadditionalfield")));
		Assert.assertEquals(response.path("batchStatus"), (hm.get(0).get("BatchStatusmissingadditonalfield")));
		Assert.assertEquals(response.path("batchNoOfClasses"), Integer.parseInt((hm.get(0).get("BatchNoOfClassesmissingadditonalfield"))));	
	
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
