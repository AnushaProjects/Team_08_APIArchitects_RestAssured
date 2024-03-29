package Payload;

import java.util.List;

public class UserReqBdyUserLoginPayload {

	private String loginStatus;
	private String password;
	private List<String> roleIds;
	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}
	private String status;
	private String userLoginEmail;
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<String> getRoleIds() {
		return roleIds;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserLoginEmail() {
		return userLoginEmail;
	}
	public void setUserLoginEmail(String userLoginEmail) {
		this.userLoginEmail = userLoginEmail;
	}
}
