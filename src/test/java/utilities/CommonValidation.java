package utilities;

import java.io.File;

import org.testng.Assert;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class CommonValidation {
	Response response;	
	String schema_path="src/test/resources/Schema";

	public void Success(Response res) {
		int Success=res.getStatusCode();
		System.out.println(Success);
	}

	//Validating header
	public void headervalidations(Response response) {

		LoggerLoad.info("Validating the header");
		Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
		Assert.assertEquals(response.getHeader("Server"), "Cowboy");
		Assert.assertEquals(response.getHeader("transfer-encoding"), "chunked");
		LoggerLoad.info("Header Validation Passed");
	}

	//Validating Schema
	public void schemavalidation(Response response,String filepath) {

		LoggerLoad.info("Schema Validation started");
		response.then().assertThat()
		.body(JsonSchemaValidator
				.matchesJsonSchema(new File(schema_path + filepath)));
		LoggerLoad.info("Schema Validation Passed");

	}

	//Statuscode validation
	public void statusValidations(Response response,int statuscode) {

		LoggerLoad.info("StatusCode: " + response.statusCode());
		int codeofstatus = response.getStatusCode();
		Assert.assertEquals(statuscode,codeofstatus);
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
			LoggerLoad.info("401 - Unauthorized: Give the authorization");
			LoggerLoad.info("Status Validation Passed");
		} else {
			LoggerLoad.info("Unexpected Status Code: " + response.statusCode());
			LoggerLoad.info("Status Validation Failed");
		}

	}

	//messageValidation
	public void messageValidations(Response response,boolean successvalue) {


		Assert.assertSame(response.jsonPath().get("success"), successvalue);
		String responsemessage = response.path("message");
		LoggerLoad.info("Response Message :" +responsemessage +" , "+successvalue);
		Assert.assertFalse(responsemessage.isEmpty());
		LoggerLoad.info("Response Validation Passed");
	}


}