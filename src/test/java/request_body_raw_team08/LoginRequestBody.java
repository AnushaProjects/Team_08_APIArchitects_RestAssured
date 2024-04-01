package request_body_raw_team08;


import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import utilities_team08.LoggerLoad;
import payload_team08.LoginPayload;


public class LoginRequestBody {
	
	LoginPayload lp=new LoginPayload();
	

		public String createLoginRequest(List<Map<String, String>> hm, String condition ) {
			LoggerLoad.info("Creating RequestBody for Login LMS Application with the login credentials: " +hm.get(0).get("Username")+" and "+hm.get(0).get("Password"));
			if(condition.equalsIgnoreCase("valid")) {
				LoggerLoad.info("Checking Login with Valid Credentials " +hm.get(0).get("Username")+" and "+hm.get(0).get("Password"));
				lp.setUserLoginEmailId(hm.get(0).get("Username"));
				lp.setPassword(hm.get(0).get("Password"));
			}
			else {
				LoggerLoad.info("Checking Login with Invalid Credentials " +hm.get(0).get("InvalidUsername")+" and "+hm.get(0).get("InvalidPassword"));
				lp.setUserLoginEmailId(hm.get(0).get("InvalidUsername"));
				lp.setPassword(hm.get(0).get("InvalidPassword"));
			}
			JSONObject loginBody=new JSONObject(lp);
			LoggerLoad.info("Converted LoginRequestBody to JSON Format " +loginBody);
			return loginBody.toString();
		    }
		
			
}
