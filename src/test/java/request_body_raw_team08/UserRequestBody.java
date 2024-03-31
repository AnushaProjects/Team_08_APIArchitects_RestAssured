package request_body_raw_team08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;

import io.restassured.path.json.JsonPath;
import payload_team08.UserPayload;
import payload_team08.UserReqBdyUserLoginPayload;
import payload_team08.UserReqBdyUserRoleMapsPayload;
import utilities_team08.ConfigReader;
import utilities_team08.ExcelReaderData;
import utilities_team08.LoggerLoad;
import utilities_team08.ReusableMethods;
import utilities_team08.ReusableVariables;

public class UserRequestBody extends ReusableVariables {

	UserPayload up=new UserPayload();
	UserReqBdyUserRoleMapsPayload roleMaps = new UserReqBdyUserRoleMapsPayload();
	UserReqBdyUserLoginPayload userLogin = new UserReqBdyUserLoginPayload();
	ReusableMethods reuse=new ReusableMethods();
	ExcelReaderData read = new ExcelReaderData();
	ConfigReader configreader=new ConfigReader();
	Properties prop =configreader.readingdata();

			public UserPayload createUserRequest(List<Map<String, String>> hm) {
				
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(hm.get(0).get("RoleIds")); 
				System.out.println(hm.get(0).get("UserComments"));
				LoggerLoad.info("Setting All the Required Fields to the Payload:");
				
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

					return up;
			    }
			
			public void negativeUserScenario(String negativedata ,String InvalidValue ) throws InvalidFormatException, IOException {
				
					userLogin=up.getUserLogin();
					LoggerLoad.info("Checking the UserId creation with input value = " );
					switch(negativedata) {
					case "Firstname":
						up.setUserFirstName(InvalidValue);
						LoggerLoad.info("Firstname = " +InvalidValue );
						break;
					case "TimeZone":
						up.setUserTimeZone(InvalidValue);
						LoggerLoad.info("TimeZone = " +InvalidValue );
						break;
					case "LinkedIn":
						up.setUserLinkedinUrl(InvalidValue);
						LoggerLoad.info("LinkedIn = " +InvalidValue );
						break;
					case "VisaStatus":
						up.setUserVisaStatus(InvalidValue);
						LoggerLoad.info("VisaStatus = " +InvalidValue );
						break;
					case "PhoneNumber":
						up.setUserPhoneNumber(InvalidValue);
						LoggerLoad.info("PhoneNumber = " +InvalidValue );
						break;
					case "LoginEmail":
						userLogin=up.getUserLogin();
						userLogin.setUserLoginEmail(InvalidValue);
						LoggerLoad.info("LoginEmail = " +InvalidValue );
						break;
					case "EduUg":
						up.setUserEduUg(InvalidValue);
						LoggerLoad.info("EduUg " +InvalidValue );
						break;
					case "EduPg":
						up.setUserEduPg(InvalidValue);
						LoggerLoad.info("EduPg " +InvalidValue );
						break;
//					case "ExistingEmailId":
//						userLogin.setUserLoginEmail(prop.getProperty("existemail"));
//						LoggerLoad.info("ExistingEmailId " +prop.getProperty("existemail") );
//						break;
//					case "ExistingPhoneNumber":
//						up.setUserPhoneNumber(prop.getProperty("existphone"));
//						LoggerLoad.info("ExistingPhoneNumber " +prop.getProperty("existphone") );
//						break;
					}
			 }
			
}
