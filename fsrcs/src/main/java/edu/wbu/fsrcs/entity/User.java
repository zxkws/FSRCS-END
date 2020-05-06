package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String username;
    private String nickName;
    private String password;
    private Date createDate;
    private Date updateDate;
    /**
     * 用户角色
     * 1:用户,2：医师,3：管理员.
     */
    private String userFlag;
    private Set<Role> roleSet = new HashSet<Role>();
    /**
     * 1:正常状态,2：用户被锁定.
     */
    private String accountStatus;
    private String physicianLicense;

    private Object data;

    private Object token;

    /*public List<Role> getRoleList() {
        String[] arr = this.userFlag.split(",");
        for(int i = 0; i< arr.length; i++){
            Role role = new Role();
            role.setRoleId(Long.parseLong(arr[i]));
            roleList.add(role);
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }*/
}
