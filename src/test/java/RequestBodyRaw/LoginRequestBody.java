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
	
	public String createLoginRequest(HashMap<String,String> hm ) {
		lp.setUserLoginEmailId(hm.get("username"));
		lp.setPassword(hm.get("password"));
		JSONObject loginBody=new JSONObject(lp);
		return loginBody.toString();
	    }
}
