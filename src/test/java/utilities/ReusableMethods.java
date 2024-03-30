package utilities;

import static io.restassured.RestAssured.*;

import org.testng.Assert;

import RequestBodyRaw.LoginRequestBody;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods extends ReusableVariables {
	LoginRequestBody loginReqbody=new LoginRequestBody(); 
	
//Login to LMS Application and creating bearer Token	
	
public String returnToken() {
		
		Response res = given().header("Content-Type","application/json").body(loginReqbody.loginBody).when().post(baseURL+"/login");
        System.out.println(res.asPrettyString());
        JsonPath gettoken = res.jsonPath();
        bearerToken = gettoken.get("token");
        System.out.println(res.statusCode());
        Assert.assertEquals(res.statusCode(), 200);
        return bearerToken;
       
		
	}
public int getRandomNumber() {
	int min=51;
	int max=1000;
return (int) ((Math.random() * (max - min)) + min);
}

}
