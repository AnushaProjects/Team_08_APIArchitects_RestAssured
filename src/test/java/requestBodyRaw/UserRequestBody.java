package requestBodyRaw;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONObject;
import payLoad.UserPayload;
import payLoad.UserPutPayload;
import payLoad.UpdateUserLoginStatusPayload;
import payLoad.UpdateUserRoleIdPayload;
import payLoad.UpdateUserRolePrgmBatchStatusPayload;
import payLoad.UpdateUserRoleStatusPayload;
import payLoad.UserLoginPayload;
import payLoad.UserRoleMapsPayload;
import payLoad.UserRoleProgramBatchesPayload;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.ReusableMethods;
import utilities.ReusableVariables;

public class UserRequestBody extends ReusableVariables{

	ConfigReader configreader = new ConfigReader();
	ExcelReader read = new ExcelReader();
	UserPayload up=new UserPayload();
	UserPutPayload pp = new UserPutPayload();
	UserLoginPayload userLogin=new UserLoginPayload();
	UserRoleMapsPayload userRoleMaps=new UserRoleMapsPayload();	
	ReusableMethods reuse=new ReusableMethods();
	UpdateUserRoleStatusPayload UserRoleStatusPayload = new UpdateUserRoleStatusPayload();
	UpdateUserRoleIdPayload UserRoleIdPayload = new UpdateUserRoleIdPayload();
	UpdateUserRolePrgmBatchStatusPayload userRolePrgmBatchStatusPayload = new UpdateUserRolePrgmBatchStatusPayload();
	UserRoleProgramBatchesPayload userPrgmBatchPayload = new UserRoleProgramBatchesPayload();
	UpdateUserLoginStatusPayload userLoginStatusPayload = new UpdateUserLoginStatusPayload();
	
	public UserPayload createUserRequest(List<Map<String, String>> hm) {

		List<String> roleIds = new ArrayList<String>(); 
		roleIds.add(hm.get(0).get("RoleIds")); 
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

		ArrayList<UserRoleMapsPayload> roleMapsList = new ArrayList<UserRoleMapsPayload>();
		userRoleMaps.setRoleId(hm.get(0).get("AdminRoleId"));
		userRoleMaps.setUserRoleStatus(hm.get(0).get("UserRoleStatus"));
		roleMapsList.add(userRoleMaps);
		up.setUserRoleMaps(roleMapsList);

		up.setUserTimeZone(hm.get(0).get("UserTimeZone"));
		up.setUserVisaStatus(hm.get(0).get("UserVisaStatus"));					

		//JSONObject userBody=new JSONObject(up);
		//return userBody.toString();
		return up;
	}
/*
	public UserPayload returnUserPayload(String sheet) throws InvalidFormatException, IOException{		
		List<Map<String, String>> hm=read.getData(path,sheet);
		return createUserRequest(hm);		
	}	
	public String convertJsonToString(UserPayload up) {
		JSONObject userBody=new JSONObject(up);
		return userBody.toString();
	}	*/

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
/*
	public UserPutPayload returnUserPutPayload(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> phm=read.getData(path,sheet);
		return putUserRequest(phm);
	}
	
	public String convertJsonToString(UserPutPayload pp) {
		JSONObject userPutBody=new JSONObject(pp);
		return userPutBody.toString();
	}*/
	
	//user role status
	
	public UpdateUserRoleStatusPayload putUserRoleStatus(List<Map<String, String>> rolestatushm) {		
		//UserRoleStatusPayload.setRoleId("R01");//roleId is an array, so whatever role id given while creating the user/admin id should be given here
		UserRoleStatusPayload.setRoleId(rolestatushm.get(0).get("RoleId"));
		UserRoleStatusPayload.setUserRoleStatus(rolestatushm.get(0).get("UserRoleStatus"));
		return UserRoleStatusPayload;	
	}
	/*
	public UpdateUserRoleStatusPayload returnUpdateUserRoleStatusPayload(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> rolestatushm=read.getData(path,sheet);
		return putUserRoleStatus(rolestatushm);
	}
	
	public String convertJsonToString(UpdateUserRoleStatusPayload UserRoleStatusPayload) {
		JSONObject userRoleStatusBody=new JSONObject(UserRoleStatusPayload);
		return userRoleStatusBody.toString();
	}*/

	
	public UpdateUserRoleStatusPayload updateInvalidUserRoleStatus(List<Map<String, String>> invalidrolestatushm) {
		UserRoleStatusPayload.setRoleId(invalidrolestatushm.get(1).get("RoleId"));
		UserRoleStatusPayload.setUserRoleStatus(invalidrolestatushm.get(1).get("UserRoleStatus"));
		return UserRoleStatusPayload;
	}
/*
	public UpdateUserRoleStatusPayload returnupdateInvalidUserRoleStatus(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> invalidrolestatushm=read.getData(path,sheet);
		return updateInvalidUserRoleStatus(invalidrolestatushm);
	}*/
	
	//put user role program batch status

	public UpdateUserRolePrgmBatchStatusPayload updateAdminRoleProgramBatchStatus(List<Map<String, String>> prgbatchhm) {
		//userRolePrgmBatchStatusPayload.setProgramId(prgbatchhm.get(0).get("ProgramId"));
		//needs program id /batch id from swati and priyanka code
		userRolePrgmBatchStatusPayload.setRoleId(prgbatchhm.get(0).get("RoleId"));
		userRolePrgmBatchStatusPayload.setUserId(prgbatchhm.get(0).get("UserId"));

		ArrayList<UserRoleProgramBatchesPayload> userRoleProgramBatchesList = new ArrayList<UserRoleProgramBatchesPayload>();
		userPrgmBatchPayload.setBatchId(prgbatchhm.get(0).get("BatchId"));
		userPrgmBatchPayload.setUserRoleProgramBatchStatus(prgbatchhm.get(0).get("UserRoleProgramBatchStatus"));
		userRoleProgramBatchesList.add(userPrgmBatchPayload);			
		userRolePrgmBatchStatusPayload.setUserRoleProgramBatches(userRoleProgramBatchesList);

		return userRolePrgmBatchStatusPayload;
	}		
/*
	public UpdateUserRolePrgmBatchStatusPayload returnupdateAdminRoleProgramBatchStatus(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> prgbatchhm=read.getData(path,sheet);
		return updateAdminRoleProgramBatchStatus(prgbatchhm);
	}
	
	public String convertJsonToString(UpdateUserRolePrgmBatchStatusPayload userRolePrgmBatchStatusPayload) {
		JSONObject userRolePrgmBatchStatusBody=new JSONObject(userRolePrgmBatchStatusPayload);
		return userRolePrgmBatchStatusBody.toString();
	}*/

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
	/*
	public UpdateUserLoginStatusPayload returnupdateUserLoginStatus(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> loginstatushm=read.getData(path,sheet);
		return updateUserLoginStatus(loginstatushm);
	}
	
	public String convertJsonToString(UpdateUserLoginStatusPayload userLoginStatusPayload) {
		JSONObject userLoginStatusBody=new JSONObject(userLoginStatusPayload);
		return userLoginStatusBody.toString();
	}*/
	
	//put - roleId

	public String updateUserRoleId(List<Map<String,String>> rolelisthm) {

		List<String> roleIds = new ArrayList<String>(); 
		roleIds.add(rolelisthm.get(0).get("RoleList"));
		UserRoleIdPayload.setUserRoleList(roleIds);

		//return UserRoleIdPayload;
		
		JSONObject userRoleListBody=new JSONObject(UserRoleIdPayload);
		return userRoleListBody.toString();
	}
	/*
	public UpdateUserRoleIdPayload returnupdateUserRoleId(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> rolelisthm=read.getData(path,sheet);
		return updateUserRoleId(rolelisthm);
	}
	
	public String convertJsonToString(UpdateUserRoleIdPayload UserRoleIdPayload) {
		JSONObject userRoleListBody=new JSONObject(UserRoleIdPayload);
		return userRoleListBody.toString();
	}*/
	//role id -put
	public UpdateUserRoleIdPayload updateInvalidUserRoleId(List<Map<String,String>> invalidrolelisthm) {
		List<String> roleIds = new ArrayList<String>(); 
		roleIds.add(invalidrolelisthm.get(1).get("RoleList"));
		UserRoleIdPayload.setUserRoleList(roleIds);

		return UserRoleIdPayload;
	}
	
	/*
	public UpdateUserRoleIdPayload returnupdateInvalidUserRoleId(String sheet) throws InvalidFormatException, IOException {
		List<Map<String, String>> invalidrolelisthm=read.getData(path,sheet);
		return updateUserRoleId(invalidrolelisthm);
	}
	
	public String convertJsonToStringinvalidroleid(UpdateUserRoleIdPayload UserRoleIdPayload) {
		JSONObject invaliduserRoleListBody=new JSONObject(UserRoleIdPayload);
		return invaliduserRoleListBody.toString();
	}*/



}