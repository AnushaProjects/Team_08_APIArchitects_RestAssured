package utilities;

import static io.restassured.RestAssured.*;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import RequestBodyRaw.LoginRequestBody;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

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


}