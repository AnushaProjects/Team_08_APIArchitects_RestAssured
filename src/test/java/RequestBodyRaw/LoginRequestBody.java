package RequestBodyRaw;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import payLoad.LoginPayload;


public class LoginRequestBody {
	LoginPayload lp=new LoginPayload();
	public String loginBody="{"
			+ "  \"password\": \"lmsHackathon@2024\",\n"
			+ "  \"userLoginEmailId\": \"numpyninja@gmail.com\"\n"
			+ "}";
	public String createLoginRequest(List<Map<String, String>> hm ) {
		System.out.println(hm.get(0).get("Username"));
		System.out.println(hm.get(0).get("Password"));
		lp.setUserLoginEmailId(hm.get(0).get("Username"));
		lp.setPassword(hm.get(0).get("Password"));
		JSONObject loginBody=new JSONObject(lp);
		return loginBody.toString();
	    }
	
		}


