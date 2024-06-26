package payload_team08;

import java.util.List;

public class UserPayload {
private String userComments;
private String userEduPg;
private String userEduUg;
private String userFirstName;
private String userId;
private String userLastName;
private String userLinkedinUrl;
private String userLocation;
private UserReqBdyUserLoginPayload userLogin;
private String userMiddleName;
private String userPhoneNumber;
private String userTimeZone;
private String userVisaStatus;
private List<UserReqBdyUserRoleMapsPayload> userRoleMaps;

//uma


public String getUserComments() {
	return userComments;
}
public void setUserComments(String userComments) {
	this.userComments = userComments;
}
public String getUserEduPg() {
	return userEduPg;
}
public void setUserEduPg(String userEduPg) {
	this.userEduPg = userEduPg;
}
public String getUserEduUg() {
	return userEduUg;
}
public void setUserEduUg(String userEduUg) {
	this.userEduUg = userEduUg;
}
public String getUserFirstName() {
	return userFirstName;
}
public void setUserFirstName(String userFirstName) {
	this.userFirstName = userFirstName;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserLastName() {
	return userLastName;
}
public void setUserLastName(String userLastName) {
	this.userLastName = userLastName;
}
public String getUserLinkedinUrl() {
	return userLinkedinUrl;
}
public void setUserLinkedinUrl(String userLinkedinUrl) {
	this.userLinkedinUrl = userLinkedinUrl;
}
public String getUserLocation() {
	return userLocation;
}
public void setUserLocation(String userLocation) {
	this.userLocation = userLocation;
}
public UserReqBdyUserLoginPayload getUserLogin() {
	return userLogin;
}
public void setUserLogin(UserReqBdyUserLoginPayload userLogin) {
	this.userLogin = userLogin;
}
public String getUserMiddleName() {
	return userMiddleName;
}
public void setUserMiddleName(String userMiddleName) {
	this.userMiddleName = userMiddleName;
}
public String getUserPhoneNumber() {
	return userPhoneNumber;
}
public void setUserPhoneNumber(String userPhoneNumber) {
	this.userPhoneNumber = userPhoneNumber;
}
public String getUserTimeZone() {
	return userTimeZone;
}
public void setUserTimeZone(String userTimeZone) {
	this.userTimeZone = userTimeZone;
}
public String getUserVisaStatus() {
	return userVisaStatus;
}
public void setUserVisaStatus(String userVisaStatus) {
	this.userVisaStatus = userVisaStatus;
}
public List<UserReqBdyUserRoleMapsPayload> getUserRoleMaps() {
	return userRoleMaps;
}
public void setUserRoleMaps(List<UserReqBdyUserRoleMapsPayload> userRoleMaps) {
	this.userRoleMaps = userRoleMaps;
}




}
