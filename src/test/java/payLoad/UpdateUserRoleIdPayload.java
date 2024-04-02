package payLoad;

import java.util.List;

public class UpdateUserRoleIdPayload {

    private List<String> userRoleList;

    public UpdateUserRoleIdPayload() {
    }

    public UpdateUserRoleIdPayload(List<String> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<String> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<String> userRoleList) {
        this.userRoleList = userRoleList;
    }
}