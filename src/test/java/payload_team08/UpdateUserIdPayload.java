package payload_team08;

import java.util.List;

public class UpdateUserIdPayload {
	 private List<String> userRoleList;

//	    public UpdateUserRoleIdPayload() {
//	    }

//	    public UpdateUserRoleIdPayload(List<String> userRoleList) {
//	        this.userRoleList = userRoleList;
//	    }

	    public List<String> getUserRoleList() {
	        return userRoleList;
	    }

	    public void setUserRoleList(List<String> userRoleList) {
	        this.userRoleList = userRoleList;
	    }
}
