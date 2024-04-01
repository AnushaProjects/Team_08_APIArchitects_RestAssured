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
		
//		  try {
		        LoggerLoad.info("Validating the header");
		        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		        Assert.assertEquals(response.getHeader("Server"), "Cowboy");
		        Assert.assertEquals(response.getHeader("transfer-encoding"), "chunked");
		        LoggerLoad.info("Header Validation Passed");
//		    } catch (AssertionError e) {
//		        LoggerLoad.info("Header Validation Failed: " + e.getMessage());
//		        LoggerLoad.info("Actual Content-Type Header: " + response.getHeader("Content-Type"));
//		        LoggerLoad.info("Actual Server Header: " + response.getHeader("Server"));
//		        LoggerLoad.info("Actual Transfer-Encoding Header: " + response.getHeader("transfer-encoding"));
//		    }
		}
	
	//Validating Schema
	public void schemavalidation(Response response,String filepath) {

//		try {
	        LoggerLoad.info("Schema Validation started");
			response.then().assertThat()
	          .body(JsonSchemaValidator
	          .matchesJsonSchema(new File(schema_path + filepath)));
	        LoggerLoad.info("Schema Validation Passed");
//	    } catch (AssertionError e) {
//	        LoggerLoad.info("Schema Validation Failed: " + e.getMessage());
//	        LoggerLoad.info("Response Body: " + response.getBody().asString());
//	    }
	}
		

	//Statuscode validation
	public void statusValidations(Response response,int statuscode,String message) {
		
		
//		
//		try {
	        LoggerLoad.info("StatusCode: " + response.statusCode());
	        int codeofstatus = response.getStatusCode();
	      
	        Assert.assertEquals(message, statuscode, codeofstatus);
	        LoggerLoad.info("Validation message: " + response.getBody().asString());

	        if (response.statusCode() == 200) {
	            LoggerLoad.info("Success - OK");
	            LoggerLoad.info("Status Validation Passed");
	        } else if (response.statusCode() == 400) {
	            LoggerLoad.info("BAD Request: check the request");
	            LoggerLoad.info("Status Validation Passed");
	        } else if (response.statusCode() == 201) {
	            LoggerLoad.info("Success - Created");
	            LoggerLoad.info("Status Validation Passed");
	        } else if (response.statusCode() == 401) {
	            LoggerLoad.info("401 - Unauthorized/InvalidEndpoint");
	            LoggerLoad.info("Status Validation Passed");
	        } else if(response.statusCode() == 405) {
	            LoggerLoad.info("Method Not Allowed: " + response.statusCode());
	            LoggerLoad.info("Status Validation Failed");
	        }
	        else {
	            LoggerLoad.info("Unexpected Status Code: " + response.statusCode());
	            LoggerLoad.info("Status Validation Failed");
	        }
	       
	        
//	    } catch (AssertionError e) {
//	        LoggerLoad.info("Assertion failed: "+e.getMessage());
//	        LoggerLoad.info("Status Validation Failed");
//	    }
	}
	
	//messageValidation
	public void messageValidations(Response response,boolean successvalue) {

//		try {
	        Assert.assertSame(response.jsonPath().get("success"), successvalue);
	        String responsemessage = response.path("message");
	        LoggerLoad.info("Response Message :" +responsemessage +" , "+successvalue);
	        Assert.assertFalse(responsemessage.isEmpty());
	        LoggerLoad.info("Response Validation Passed");
//	        
//	    } catch (AssertionError e) {
//	        String responsemessage = response.path("message");
//	        Assert.assertTrue(responsemessage.isEmpty());
//	        LoggerLoad.info("Response Message :" +responsemessage +" , "+successvalue);
//	        LoggerLoad.info("Validation failed");
//	    }
	
	}
}
