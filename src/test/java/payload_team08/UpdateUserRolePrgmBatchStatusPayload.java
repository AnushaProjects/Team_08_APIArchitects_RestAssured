package payload_team08;

import java.util.List;

public class UpdateUserRolePrgmBatchStatusPayload {
	
	private String programId;
	private String roleId;
	private String userId;
	private List<UserRoleProgramBatchesPayload> userRoleProgramBatches;

	public List<UserRoleProgramBatchesPayload> getUserRoleProgramBatches() {
		return userRoleProgramBatches;
	}
	public void setUserRoleProgramBatches(List<UserRoleProgramBatchesPayload> userRoleProgramBatches) {
		this.userRoleProgramBatches = userRoleProgramBatches;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
		
}