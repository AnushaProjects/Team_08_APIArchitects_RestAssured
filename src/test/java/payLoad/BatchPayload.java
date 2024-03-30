package payLoad;

public class BatchPayload {
private String batchDescription;
private String batchName;
private String batchNoOfClasses;
private String batchStatus;
private String programId;
public String getBatchDescription() {
	return batchDescription;
}

public void setBatchDescription(String batchDescription) {
	this.batchDescription = batchDescription;
}

public String getBatchName() {
	return batchName;
}

public void setBatchName(String batchName) {
	this.batchName = batchName;
}

public String getBatchNoOfClasses() {
	return batchNoOfClasses;
}

public void setBatchNoOfClasses(String batchNoOfClasses) {
	this.batchNoOfClasses = batchNoOfClasses;
}

public String getBatchStatus() {
	return batchStatus;
}

public void setBatchStatus(String batchStatus) {
	this.batchStatus = batchStatus;
}

public String getProgramId() {
	return programId;
}

public void setProgramId(String programId) {
	this.programId = programId;
}

}
