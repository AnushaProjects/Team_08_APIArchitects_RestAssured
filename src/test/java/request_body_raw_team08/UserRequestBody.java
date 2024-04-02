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
import payload_team08.UpdateUserLoginStatusPayload;
import payload_team08.UpdateUserRoleIdPayload;
import payload_team08.UpdateUserRolePrgmBatchStatusPayload;
import payload_team08.UserPayload;
import payload_team08.UserPutPayload;
import payload_team08.UserReqBdyUserLoginPayload;
import payload_team08.UserReqBdyUserRoleMapsPayload;
import payload_team08.UserRoleProgramBatchesPayload;
import payload_team08.UpdateUserRoleStatusPayload;
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
	UpdateUserRoleStatusPayload usp=new UpdateUserRoleStatusPayload();
	UpdateUserIdPayload updaterole=new UpdateUserIdPayload();
	UserPutPayload pp = new UserPutPayload();
	UpdateUserRoleStatusPayload UserRoleStatusPayload = new UpdateUserRoleStatusPayload();
	UpdateUserRoleIdPayload UserRoleIdPayload = new UpdateUserRoleIdPayload();
	UpdateUserRolePrgmBatchStatusPayload userRolePrgmBatchStatusPayload = new UpdateUserRolePrgmBatchStatusPayload();
	UserRoleProgramBatchesPayload userPrgmBatchPayload = new UserRoleProgramBatchesPayload();
	UpdateUserLoginStatusPayload userLoginStatusPayload = new UpdateUserLoginStatusPayload();

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
				public UpdateUserRoleStatusPayload updateUserRole(List<Map<String, String>> hm) {
					System.out.println("Inside PUT");
					
					LoggerLoad.info("Setting All the Required Fields to the Payload:");
					
					usp.setRoleId(hm.get(0).get("AdminRoleId"));
					usp.setUserRoleStatus(hm.get(0).get("UpdateRoleStatus"));
					return usp;
				    }
				
				public UpdateUserRoleStatusPayload returnUpdateRoleStatus(String sheet) throws InvalidFormatException, IOException  {
					
					List<Map<String, String>> hm=read.getData(path,sheet);
					return updateUserRole(hm);
					
				}

			
			public String convertJsonToString(UserPayload up) {
				JSONObject userBody=new JSONObject(up);
				return userBody.toString();
			}
			public String convertJsonToString2(UpdateUserRoleStatusPayload usp) {
				JSONObject userBody=new JSONObject(usp);
				return userBody.toString();
			}
			
//			anusha
//			public UpdateUserIdPayload updateUserRoleId(List<Map<String, String>> hm) {
//				System.out.println("Inside PUT");
//				List<String> roleIds = new ArrayList<String>(); 
//				roleIds.add(hm.get(0).get("UpdateRoleIds")); 
//
//				updaterole.setUserRoleList(roleIds);
//				return updaterole;
//			    }
//			
//			public UpdateUserIdPayload returnUpdateRoleId(String sheet) throws InvalidFormatException, IOException  {
//				
//				List<Map<String, String>> hm=read.getData(path,sheet);
//				return updateUserRoleId(hm);
//				
//			}
//
//		
		public String convertJsonToStringid(UpdateUserIdPayload updaterole) {
			JSONObject userBody=new JSONObject(updaterole);
			return userBody.toString();
		}
			
		
		
		public UpdateUserIdPayload updateUserassignpb(List<Map<String, String>> hm) {
			System.out.println("Inside PUT");
			List<String> roleIds = new ArrayList<String>(); 
			roleIds.add(hm.get(0).get("UpdateRoleIds")); 

			updaterole.setUserRoleList(roleIds);
			return updaterole;
		    }
		
//		public UpdateUserIdPayload returnUpdateassignpb(String sheet) throws InvalidFormatException, IOException  {
//			
//			List<Map<String, String>> hm=read.getData(path,sheet);
//			return updateUserRoleId(hm);
//			
//		}

	
	public String convertJsonToassign(UpdateUserIdPayload updaterole) {
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
			
			//Uma
			
			//put user info valid

			public UserPutPayload putUserRequest(List<Map<String, String>> phm) {
				pp.setUserComments(phm.get(0).get("UserComments"));
				pp.setUserEduPg(phm.get(0).get("UserEduPg"));
				pp.setUserEduUg(phm.get(0).get("UserEduUg"));
				pp.setUserFirstName(phm.get(0).get("UserFirstName"));
				pp.setUserId(phm.get(0).get("UserId"));
				pp.setUserLastName(phm.get(0).get("UserLastName"));
				pp.setUserLinkedinUrl(phm.get(0).get("UserLinkedinUrl"));
				pp.setUserLocation(phm.get(0).get("UserLocation"));
				pp.setUserLoginEmail(phm.get(0).get("UserLoginEmail"));
				pp.setUserMiddleName(phm.get(0).get("UserMiddleName"));
				pp.setUserPhoneNumber(phm.get(0).get("UserPhoneNumber"));
				pp.setUserTimeZone(phm.get(0).get("UserTimeZone"));
				pp.setUserVisaStatus(phm.get(0).get("UserVisaStatus"));

				return pp;
			}

			public UserPutPayload returnUserPutPayload(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> phm=read.getData(path,sheet);
				return putUserRequest(phm);
			}

			public String convertJsonToString(UserPutPayload pp) {
				JSONObject userPutBody=new JSONObject(pp);
				return userPutBody.toString();
			}

			//user role status

			public UpdateUserRoleStatusPayload putUserRoleStatus(List<Map<String, String>> rolestatushm) {		
				//UserRoleStatusPayload.setRoleId("R01");//roleId is an array, so whatever role id given while creating the user/admin id should be given here
				UserRoleStatusPayload.setRoleId(rolestatushm.get(0).get("RoleId"));
				UserRoleStatusPayload.setUserRoleStatus(rolestatushm.get(0).get("UserRoleStatus"));
				return UserRoleStatusPayload;	
			}

			public UpdateUserRoleStatusPayload returnUpdateUserRoleStatusPayload(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> rolestatushm=read.getData(path,sheet);
				return putUserRoleStatus(rolestatushm);
			}

			public String convertJsonToString(UpdateUserRoleStatusPayload UserRoleStatusPayload) {
				JSONObject userRoleStatusBody=new JSONObject(UserRoleStatusPayload);
				return userRoleStatusBody.toString();
			}


			public UpdateUserRoleStatusPayload updateInvalidUserRoleStatus(List<Map<String, String>> invalidrolestatushm) {
				UserRoleStatusPayload.setRoleId(invalidrolestatushm.get(1).get("RoleId"));
				UserRoleStatusPayload.setUserRoleStatus(invalidrolestatushm.get(1).get("UserRoleStatus"));
				return UserRoleStatusPayload;
			}

			public UpdateUserRoleStatusPayload returnupdateInvalidUserRoleStatus(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> invalidrolestatushm=read.getData(path,sheet);
				return updateInvalidUserRoleStatus(invalidrolestatushm);
			}

			public String convertJsonToStringupdateInvalidUserRoleStatus(UpdateUserRoleStatusPayload UserRoleStatusPayload) {
				JSONObject invalidRoleStatusBody=new JSONObject(UserRoleStatusPayload);
				return invalidRoleStatusBody.toString();
			}
			//put user role program batch status

			public UpdateUserRolePrgmBatchStatusPayload updateAdminRoleProgramBatchStatus(List<Map<String, String>> prgbatchhm) {
				//userRolePrgmBatchStatusPayload.setProgramId(prgbatchhm.get(0).get("ProgramId"));
				//needs program id /batch id from swati and priyanka code
				userRolePrgmBatchStatusPayload.setRoleId(prgbatchhm.get(0).get("RoleId"));
				userRolePrgmBatchStatusPayload.setUserId(prgbatchhm.get(0).get("UserId"));

				ArrayList<UserRoleProgramBatchesPayload> userRoleProgramBatchesList = new ArrayList<UserRoleProgramBatchesPayload>();
				userPrgmBatchPayload.setBatchId(prgbatchhm.get(0).get(batchId));
				userPrgmBatchPayload.setProgramId(prop.getProperty("program_Id_chaining"));
				userPrgmBatchPayload.setUserRoleProgramBatchStatus(prgbatchhm.get(0).get("UserRoleProgramBatchStatus"));
				userRoleProgramBatchesList.add(userPrgmBatchPayload);			
				userRolePrgmBatchStatusPayload.setUserRoleProgramBatches(userRoleProgramBatchesList);

				return userRolePrgmBatchStatusPayload;
			}		

			public UpdateUserRolePrgmBatchStatusPayload returnupdateAdminRoleProgramBatchStatus(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> prgbatchhm=read.getData(path,sheet);
				return updateAdminRoleProgramBatchStatus(prgbatchhm);
			}

			public String convertJsonToString(UpdateUserRolePrgmBatchStatusPayload userRolePrgmBatchStatusPayload) {
				JSONObject userRolePrgmBatchStatusBody=new JSONObject(userRolePrgmBatchStatusPayload);
				return userRolePrgmBatchStatusBody.toString();
			}

			//login status - put
			public UpdateUserLoginStatusPayload updateUserLoginStatus(List<Map<String, String>> loginstatushm) {

				userLoginStatusPayload.setLoginStatus(loginstatushm.get(0).get("LoginStatus"));
				userLoginStatusPayload.setPassword(loginstatushm.get(0).get("Password"));

				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(loginstatushm.get(0).get("RoleIds"));

				userLoginStatusPayload.setStatus(loginstatushm.get(0).get("Status"));
				userLoginStatusPayload.setUserLoginEmail(configreader.getEmaildId());
				

				return 	userLoginStatusPayload;
			}

			public UpdateUserLoginStatusPayload returnupdateUserLoginStatus(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> loginstatushm=read.getData(path,sheet);
				return updateUserLoginStatus(loginstatushm);
			}

			public String convertJsonToString(UpdateUserLoginStatusPayload userLoginStatusPayload) {
				JSONObject userLoginStatusBody=new JSONObject(userLoginStatusPayload);
				return userLoginStatusBody.toString();
			}

			//put - roleId

			public UpdateUserRoleIdPayload updateUserRoleId(List<Map<String,String>> rolelisthm) {

				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(rolelisthm.get(0).get("RoleList"));
				UserRoleIdPayload.setUserRoleList(roleIds);

				return UserRoleIdPayload;

				//		JSONObject userRoleListBody=new JSONObject(UserRoleIdPayload);
				//		return userRoleListBody.toString();
			}

			public UpdateUserRoleIdPayload returnupdateUserRoleId(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> rolelisthm=read.getData(path,sheet);
				return updateUserRoleId(rolelisthm);
			}

			public String convertJsonToString(UpdateUserRoleIdPayload UserRoleIdPayload) {
				JSONObject userRoleListBody=new JSONObject(UserRoleIdPayload);
				return userRoleListBody.toString();
			}
			//role id -put
			public UpdateUserRoleIdPayload updateInvalidUserRoleId(List<Map<String,String>> invalidrolelisthm) {
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(invalidrolelisthm.get(1).get("RoleList"));
				UserRoleIdPayload.setUserRoleList(roleIds);

				return UserRoleIdPayload;
			}	
			public UpdateUserRoleIdPayload returnupdateInvalidUserRoleId(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> invalidrolelisthm=read.getData(path,sheet);
				return updateUserRoleId(invalidrolelisthm);
			}
			public String convertJsonToStringinvalidroleid(UpdateUserRoleIdPayload UserRoleIdPayload) {
				JSONObject invaliduserRoleListBody=new JSONObject(UserRoleIdPayload);
				return invaliduserRoleListBody.toString();
			}
			public UpdateUserRoleIdPayload updateExistingUserRoleId(List<Map<String,String>> existingrolelisthm) {
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(existingrolelisthm.get(2).get("RoleList"));
				UserRoleIdPayload.setUserRoleList(roleIds);

				return UserRoleIdPayload;
			}	

			public UpdateUserRoleIdPayload returnupdateExistingUserRoleId(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> existingrolelisthm=read.getData(path,sheet);
				return updateUserRoleId(existingrolelisthm);
			}
			public String convertJsonToStringexistingroleid(UpdateUserRoleIdPayload UserRoleIdPayload) {
				JSONObject existinguserRoleListBody=new JSONObject(UserRoleIdPayload);
				return existinguserRoleListBody.toString();
			}
			public UpdateUserRoleIdPayload updateInvalidIdUserRoleId(List<Map<String,String>> invalididrolelisthm) {
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(invalididrolelisthm.get(1).get("RoleList"));
				UserRoleIdPayload.setUserRoleList(roleIds);

				return UserRoleIdPayload;
			}	
			public UpdateUserRoleIdPayload returnupdateInvalidIdUserRoleId(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> invalididrolelisthm=read.getData(path,sheet);
				return updateUserRoleId(invalididrolelisthm);
			}
			public String convertJsonToStringupdateInvalidIdUserRoleId(UpdateUserRoleIdPayload UserRoleIdPayload) {
				JSONObject invaididuserRoleListBody=new JSONObject(UserRoleIdPayload);
				return invaididuserRoleListBody.toString();
			}			

//			public UpdateUserRoleIdPayload updateInvalidRoleStatus(List<Map<String,String>> invalididrolestatushm) {
//				List<String> roleIds = new ArrayList<String>(); 
//				roleIds.add(invalididrolestatushm.get(1).get("RoleList"));
//				UserRoleIdPayload.setUserRoleList(roleIds);
//				return UserRoleIdPayload;
//			}	
//			public UpdateUserRoleIdPayload returnupdateInvalidRoleStatus(String sheet) throws InvalidFormatException, IOException {
//							List<Map<String, String>> invalidrolelisthm=read.getData(path,sheet);
//							return updateUserRoleId(invalidrolelisthm);
//			}
//			public String convertJsonToStringupdateInvalidRoleStatus(UpdateUserRoleIdPayload UserRoleIdPayload) {
//				JSONObject invaiduserRolestatusBody=new JSONObject(UserRoleIdPayload);
//				return invaiduserRolestatusBody.toString();
//			}		
			public UpdateUserRoleIdPayload updateRoleStatusOfNonexistingRoleId(List<Map<String,String>> rolestatusofnonexistingroleidhm) {
				List<String> roleIds = new ArrayList<String>(); 
				roleIds.add(rolestatusofnonexistingroleidhm.get(2).get("RoleList"));
				UserRoleIdPayload.setUserRoleList(roleIds);
				return UserRoleIdPayload;
			}	

			public UpdateUserRoleIdPayload returnupdateRoleStatusOfNonexistingRoleId(String sheet) throws InvalidFormatException, IOException {
				List<Map<String, String>> rolestatusofnonexistroleidlisthm=read.getData(path,sheet);
				return updateUserRoleId(rolestatusofnonexistroleidlisthm);
			}
			public String convertJsonToStringupdateRoleStatusOfNonexistingRoleId(UpdateUserRoleIdPayload UserRoleIdPayload) {
				JSONObject RolestatusofnonexistroleidBody=new JSONObject(UserRoleIdPayload);
				return RolestatusofnonexistroleidBody.toString();
			}	

			
			
}
