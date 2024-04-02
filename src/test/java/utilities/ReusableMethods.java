package utilities;

import static io.restassured.RestAssured.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import org.testng.Assert;

import requestBodyRaw.LoginRequestBody;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReusableMethods extends ReusableVariables {
	
	public String autoEmail() {
		long random_email = System.currentTimeMillis();
		String email="abc"+random_email;
		return email;	
	}
	 
	 public String autoPhoneNumber() {
		 Random rand = new Random();

		    String userPhoneNumber;
			int num1, num2, num3;
		    num1 = rand.nextInt (900) + 100;
		    num2 = rand.nextInt (643) + 100;
		    num3 = rand.nextInt (9000) + 1000;
		    userPhoneNumber=num1+""+num2+""+num3;
			return userPhoneNumber;
	 }
	 
	//public static RequestSpecification req;
		public static RequestSpecification requestSpecification() throws IOException
		{		
			if(reqspec==null)
			{
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			reqspec=new RequestSpecBuilder().setBaseUri(baseURL)
					 .addFilter(RequestLoggingFilter.logRequestTo(log))
					 .addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
			 return reqspec;
			}
			return reqspec;	
			
		}
	
//Login to LMS Application and creating bearer Token	
	
}