package payload_team08;

public class UserRoleMapsPayload {
	private String roleId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserRoleStatus() {
		return userRoleStatus;
	}
	public void setUserRoleStatus(String userRoleStatus) {
		this.userRoleStatus = userRoleStatus;
	}
	private String userRoleStatus;
}
