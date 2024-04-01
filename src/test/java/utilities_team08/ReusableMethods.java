package utilities_team08;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payload_team08.UserPayload;
import payload_team08.UserReqBdyUserLoginPayload;
import request_body_raw_team08.LoginRequestBody;

public class ReusableMethods extends ReusableVariables {
	
	UserPayload userpayload;
	UserReqBdyUserLoginPayload userLogin;
	Response userResponse;
 public String autoEmail() {
	long random_email = System.currentTimeMillis();
	String email="abc"+random_email+"@gmail.com";
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
 
 public int getRandomNumber() {
		int min=51;
		int max=1000;
	return (int) ((Math.random() * (max - min)) + min);
	}
 
 public String autoName() {
	 Random rand = new Random();

	   String name="Team08APIArchitect"+getRandomNumber();
	   return name;
 }
 
}