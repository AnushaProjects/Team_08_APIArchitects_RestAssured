package request_body_raw_team08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;

import io.restassured.path.json.JsonPath;
import payload_team08.UpdateUserIdPayload;
import payload_team08.UserPayload;
import payload_team08.UserReqBdyUserLoginPayload;
import payload_team08.UserReqBdyUserRoleMapsPayload;
import payload_team08.UserStatusPayload;
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
	UserStatusPayload usp=new UserStatusPayload();
	UpdateUserIdPayload updaterole=new UpdateUserIdPayload();

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
public UserPayload returnUserPayload(String sheet) throws InvalidFormatException, IOException  {
				
				List<Map<String, String>> hm=read.getData(path,sheet);
				return createUserRequest(hm);
				
			}
public UserPayload updateUserRequest(List<Map<String, String>> hm) {
				System.out.println("Inside PUT");
				
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(hm.get(0).get("RoleIds")); 
				System.out.println(hm.get(0).get("UserComments"));
				LoggerLoad.info("Setting All the Required Fields to the Payload:");
				
					
					up.setUserFirstName(hm.get(0).get("UpdateFirstname"));
					//up.setUserId(hm.get(0).get("UserId"));
					up.setUserLastName(hm.get(0).get("UpdateLastName"));
					
					up.setUserPhoneNumber(reuse.autoPhoneNumber());
					
					
					up.setUserTimeZone(hm.get(0).get("UpdateTimezone"));
					up.setUserVisaStatus(hm.get(0).get("UpdateVisa"));

					return up;
			    }
				public UserPayload returnUserPayloadput(String sheet) throws InvalidFormatException, IOException  {
					
					List<Map<String, String>> hm=read.getData(path,sheet);
					return updateUserRequest(hm);
					
				}
				public UserStatusPayload updateUserRole(List<Map<String, String>> hm) {
					System.out.println("Inside PUT");
					
					LoggerLoad.info("Setting All the Required Fields to the Payload:");
					
					usp.setRoleId(hm.get(0).get("AdminRoleId"));
					usp.setUserRoleStatus(hm.get(0).get("UpdateRoleStatus"));
					return usp;
				    }
				
				public UserStatusPayload returnUpdateRoleStatus(String sheet) throws InvalidFormatException, IOException  {
					
					List<Map<String, String>> hm=read.getData(path,sheet);
					return updateUserRole(hm);
					
				}

			
			public String convertJsonToString(UserPayload up) {
				JSONObject userBody=new JSONObject(up);
				return userBody.toString();
			}
			public String convertJsonToString2(UserStatusPayload usp) {
				JSONObject userBody=new JSONObject(usp);
				return userBody.toString();
			}
			
			
			public UpdateUserIdPayload updateUserRoleId(List<Map<String, String>> hm) {
				System.out.println("Inside PUT");
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(hm.get(0).get("UpdateRoleIds")); 

				updaterole.setUserRoleList(roleIds);
				return updaterole;
			    }
			
			public UpdateUserIdPayload returnUpdateRoleId(String sheet) throws InvalidFormatException, IOException  {
				
				List<Map<String, String>> hm=read.getData(path,sheet);
				return updateUserRoleId(hm);
				
			}

		
		public String convertJsonToStringid(UpdateUserIdPayload updaterole) {
			JSONObject userBody=new JSONObject(updaterole);
			return userBody.toString();
		}
			
			
			public String negativeUserScenario(String negativedata ,String InvalidValue ) throws InvalidFormatException, IOException {
				returnUserPayload("UserModuleMandatory");
//				List<Map<String, String>> hm=read.getData(path,"UserModuleMandatory");
//				up = createUserRequest(hm);
				
					userLogin=up.getUserLogin();
					LoggerLoad.info("Checking the UserId creation with input value = " );
					System.out.println("inside negative");
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
						up.setUserPhoneNumber("12345675");
						LoggerLoad.info("PhoneNumber =  12345678" );
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
						
					}
					return convertJsonToString(up);
//					JSONObject userBody=new JSONObject(up);
//					return userBody;
					
			 }
			
			public String putegativeUserScenario(String InvalidValue ) throws InvalidFormatException, IOException {
				
				returnUserPayload("UserModuleMandatory");

					//userLogin=up.getUserLogin();
					LoggerLoad.info("Updating the UserId creation with input value = " );
					System.out.println("inside negative");
					switch(InvalidValue) {
					case "Firstname":
						up.setUserFirstName(InvalidValue);
						LoggerLoad.info("Firstname = " +InvalidValue );
						break;
					case "TimeZone":
						up.setUserTimeZone(InvalidValue);
						LoggerLoad.info("TimeZone = " +InvalidValue );
						break;
					
					case "VisaStatus":
						up.setUserVisaStatus(InvalidValue);
						LoggerLoad.info("VisaStatus = " +InvalidValue );
						break;
					case "PhoneNumber":
						up.setUserPhoneNumber(InvalidValue);
						LoggerLoad.info("PhoneNumber =  "+InvalidValue );
						break;
						
					}
					return convertJsonToString(up);
//					JSONObject userBody=new JSONObject(up);
//					return userBody;
					
			 }
			
			
}
