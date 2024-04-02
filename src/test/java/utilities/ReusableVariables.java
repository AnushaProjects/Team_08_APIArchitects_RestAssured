package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ReusableVariables {
	
	ExcelReader read = new ExcelReader();
	static ConfigReader configreader=new ConfigReader();
	static Properties prop =configreader.readingdata();	
	public static String baseURL="https://lms-marchapi-hackathon-a258d2bbd43b.herokuapp.com/lms";
	public String Programpostendpoint="/saveprogram";
	public String path=".\\src\\test\\resources\\TestData\\Team_08_API Architects_LMSTestData.xlsx";
	public String invalid_endpoint="/user/roleId/{userId}";
	public String invalid_Id="Uabc";
			
	public static RequestSpecification reqspec= given()
			.header("Content-Type","application/json")
			.header("Authorization","Bearer " + prop.getProperty("bearer"));
	
	public RequestSpecification noauth_req_post=given()
			.header("Content-Type","application/json");

}