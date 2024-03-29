package utilities;

import static io.restassured.RestAssured.given;

import java.util.Properties;

import io.restassured.specification.RequestSpecification;

public class ReusableVariables {
	
	ExcelREaderData read = new ExcelREaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();
	
	public String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	
	public String path="/Users/anushakarthick/NumpyNinja/Anusha_Team8_APIArchitects_RestAssured/src/test/resources/TestData/Team_08_API Architects_LMSTestData.xlsx";
	public RequestSpecification reqspec=given()
			.header("Content-Type","application/json")
			.header("Authorization","Bearer " + prop.getProperty("bearer"));
	
}
