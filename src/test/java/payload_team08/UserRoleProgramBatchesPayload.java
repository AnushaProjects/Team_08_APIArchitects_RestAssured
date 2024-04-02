package payload_team08;

public class UserRoleProgramBatchesPayload {
	private String batchId;
	private String userRoleProgramBatchStatus;
	private String programId;
	
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getUserRoleProgramBatchStatus() {
		return userRoleProgramBatchStatus;
	}
	public void setUserRoleProgramBatchStatus(String userRoleProgramBatchStatus) {
		this.userRoleProgramBatchStatus = userRoleProgramBatchStatus;
	}
}
