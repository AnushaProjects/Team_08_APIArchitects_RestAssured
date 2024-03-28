package RequestBodyRaw;


import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import Payload.LoginPayload;


public class LoginRequestBody {
	
	LoginPayload lp=new LoginPayload();
	

		public String createLoginRequest(List<Map<String, String>> hm, String condition ) {
			System.out.println(hm.get(0).get("Username"));
			System.out.println(hm.get(0).get("Password"));
			if(condition.equalsIgnoreCase("valid")) {
				lp.setUserLoginEmailId(hm.get(0).get("Username"));
				lp.setPassword(hm.get(0).get("Password"));
			}
			else {
				lp.setUserLoginEmailId(hm.get(0).get("InvalidUsername"));
				lp.setPassword(hm.get(0).get("InvalidPassword"));
			}
			JSONObject loginBody=new JSONObject(lp);
			return loginBody.toString();
		    }
		
			
}
