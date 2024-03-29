package RequestBodyRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import Payload.UserReqBdyUserLoginPayload;
import Payload.UserPayload;
import Payload.UserReqBdyUserRoleMapsPayload;
import utilities.ReusableMethods;

public class UserRequestBody {

	UserPayload up=new UserPayload();
	UserReqBdyUserRoleMapsPayload roleMaps = new UserReqBdyUserRoleMapsPayload();
	UserReqBdyUserLoginPayload userLogin = new UserReqBdyUserLoginPayload();
	ReusableMethods reuse=new ReusableMethods();
	
		

			public String createUserRequest(List<Map<String, String>> hm) {
				
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(hm.get(0).get("RoleIds")); 
		        
				System.out.println(hm.get(0).get("UserComments"));
				//System.out.println(hm.get(0).get("Password"));
					up.setUserComments(hm.get(0).get("UserComments"));
					up.setUserEduPg(hm.get(0).get("UserEduPg"));
					up.setUserEduUg(hm.get(0).get("UserEduUg"));
					up.setUserFirstName(hm.get(0).get("UserFirstName"));
					up.setUserId(hm.get(0).get("UserId"));
					up.setUserLastName(hm.get(0).get("UserLastName"));
					up.setUserLinkedinUrl(hm.get(0).get("UserLinkedinUrl"));
					up.setUserLocation(hm.get(0).get("UserLocation"));
					
					userLogin.setLoginStatus(hm.get(0).get("LoginStatus"));
					userLogin.setPassword(hm.get(0).get("Password"));
					userLogin.setRoleIds(roleIds);
					userLogin.setStatus(hm.get(0).get("Status"));
					userLogin.setUserLoginEmail(reuse.autoEmail());
					up.setUserLogin(userLogin);
					
					up.setUserMiddleName(hm.get(0).get("UserMiddleName"));
					up.setUserPhoneNumber(reuse.autoPhoneNumber());
					
					ArrayList<UserReqBdyUserRoleMapsPayload> roleMapsList = new ArrayList<UserReqBdyUserRoleMapsPayload>();
					roleMaps.setRoleId(hm.get(0).get("AdminRoleId"));
					roleMaps.setUserRoleStatus(hm.get(0).get("UserRoleStatus"));
					roleMapsList.add(roleMaps);
					up.setUserRoleMaps(roleMapsList);
					
					up.setUserTimeZone(hm.get(0).get("UserTimeZone"));
					up.setUserVisaStatus(hm.get(0).get("UserVisaStatus"));
					
				
					JSONObject userBody=new JSONObject(up);
					return userBody.toString();
			    }
			
}
