package utilities_team08;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ReusableVariables {
	
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	
	public String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	
	public String path="src/test/resources/TestData/Team_08_API Architects_LMSTestData.xlsx";

	
	public RequestSpecification auth_req_post=given()
			.header("Content-Type","application/json")
			.header("Authorization","Bearer " + prop.getProperty("bearer"));
	
	public RequestSpecification noauth_req_post=given()
			.header("Content-Type","application/json");
	
	public String invalid_endpoint="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms/log";
	public String invalid_Id="Uabc";
	

}