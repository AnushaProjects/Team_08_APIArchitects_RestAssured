package utilities_team08;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.Matchers;

import io.cucumber.core.logging.Logger;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class CommonValidation {

	Response response;
	ExcelReaderData read = new ExcelReaderData();
	String schema_path="src/test/resources/Schema";

	
	//Validating header
	public void headervalidations(Response response) {
		
		LoggerLoad.info("Validing the header");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertEquals(response.getHeader("Server"), "Cowboy");
		Assert.assertEquals(response.getHeader("transfer-encoding"), "chunked");
		
	}
	
	//Validating Schema
	public void schemavalidation(Response response,String filepath) {
		LoggerLoad.info("Schema VAlidation started");
		response.then().assertThat()
	      .body(JsonSchemaValidator.
		 matchesJsonSchema(new File(schema_path +filepath)));
		LoggerLoad.info("Schema Validation ended");
		}
		

	//Statuscode validation
	public void statusValidations(Response response,int statuscode) {
		LoggerLoad.info("StatusCode"+response.statusCode());
		int codeofstatus=response.getStatusCode();
		Assert.assertEquals(codeofstatus,statuscode);
		if(response.statusCode()==200) {
			LoggerLoad.info("Success - OK ");
		}
		else if(response.statusCode()==400) {
			LoggerLoad.info("BAD Request : check the request ");
		}
		else if(response.statusCode()==201) {
			LoggerLoad.info("Success - Created");
		}
		else if(response.statusCode()==401) {
			LoggerLoad.info("401-Unauthorized : Give the authorization");
		}
	}
	
	
//public void messageValidations(Response response,boolean successvalue) {
//	//response.then().assertThat().body("success", Matchers.equalTo(successvalue));
//	//System.out.println(response.jsonPath().get("success"));
//	Assert.assertSame(response.jsonPath().get("success"),successvalue);
//   String responsemessage=(response.path("message"));
//   responsemessage.isEmpty();
// Assert.assertFalse(responsemessage.isEmpty());
//
//}
}
