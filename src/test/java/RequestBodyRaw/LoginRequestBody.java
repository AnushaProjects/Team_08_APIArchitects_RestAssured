package RequestBodyRaw;

import java.util.HashMap;

import org.json.JSONObject;

import Payload.LoginPayload;


public class LoginRequestBody {
	
	LoginPayload lp=new LoginPayload();
	
//	public String loginBody1="{"
//			+ "  \"password\": \"lmsHackathon@2024\",\n"
//			+ "  \"userLoginEmailId\": \"numpyninja@gmail.com\"\n"
//			+ "}";
	
	public String createLoginRequest(HashMap<String,String> hm,String condition ) {
		if(condition.equalsIgnoreCase("valid")) {
			lp.setUserLoginEmailId(hm.get("username"));
			lp.setPassword(hm.get("password"));
			}
			else {
					lp.setUserLoginEmailId(hm.get("invalidusername"));
					lp.setPassword(hm.get("invalidpassword"));
					}
			JSONObject loginBody=new JSONObject(lp);
			return loginBody.toString();
		    }
}
