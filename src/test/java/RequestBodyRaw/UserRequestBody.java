package RequestBodyRaw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import Payload.UserPayload;
import Payload.UserReqBdyUserLoginPayload;
import Payload.UserReqBdyUserRoleMapsPayload;


public class UserRequestBody {

	UserPayload up=new UserPayload();
	UserReqBdyUserLoginPayload userLogin=new UserReqBdyUserLoginPayload();
	UserReqBdyUserRoleMapsPayload userRoleMaps=new UserReqBdyUserRoleMapsPayload();
	
	
		

			public String createUserRequest(List<Map<String, String>> hm) {
				
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(hm.get(0).get("RoleIds")); 
		        
				System.out.println(hm.get(0).get("UserComments"));
				System.out.println(hm.get(0).get("UserPhoneNumber"));
					up.setUserComments(hm.get(0).get("UserComments"));
					up.setUserEduPg(hm.get(0).get("UserEduPg"));
					up.setUserEduUg(hm.get(0).get("UserEduUg"));
					up.setUserFirstName(hm.get(0).get("UserFirstName"));
					up.setUserId(hm.get(0).get("UserId"));
					up.setUserLastName(hm.get(0).get("UserLastName"));
					up.setUserLinkedinUrl(hm.get(0).get("UserLinkedinUrl"));
					up.setUserLocation(hm.get(0).get("UserLocation"));
					
					//Object(object inside Array)
					userLogin.setLoginStatus(hm.get(0).get("LoginStatus"));
					userLogin.setPassword(hm.get(0).get("Password"));
					userLogin.setStatus(hm.get(0).get("Status"));
					userLogin.setUserLoginEmail(hm.get(0).get("UserLoginEmail"));
					userLogin.setRoleIds(roleIds);
					up.setUserLogin(userLogin);
					
					//ArrayList(Object Inside Array)
					ArrayList<UserReqBdyUserRoleMapsPayload> roleMapsList = new ArrayList<UserReqBdyUserRoleMapsPayload>();
					userRoleMaps.setRoleId(hm.get(0).get("RoleId"));
					userRoleMaps.setUserRoleStatus(hm.get(0).get("UserRoleStatus"));
					roleMapsList.add(userRoleMaps);
					up.setUserRoleMaps(roleMapsList);
					
					up.setUserMiddleName(hm.get(0).get("UserMiddleName"));
					up.setUserPhoneNumber(hm.get(0).get("UserPhoneNumber"));
					up.setUserTimeZone(hm.get(0).get("UserTimeZone"));
					up.setUserVisaStatus(hm.get(0).get("UserVisaStatus"));
					
				
					JSONObject userBody=new JSONObject(up);
					return userBody.toString();
			    }
			
}
