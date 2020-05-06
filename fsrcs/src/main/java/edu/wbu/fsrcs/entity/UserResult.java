package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResult implements Serializable {
    private String userId;
    private String username;
    private String nickName;
    private Date createDate;
    private Date updateDate;
    Object token;
    /**
     * 用户角色
     * 1:普通用户,2：医师,3：管理员.
     */
    private String userFlag;
    private List<String> roleIds = new ArrayList<>();
    /**
     * 1:正常状态,2：用户被锁定.
     */
    private String accountStatus;
    private String physicianLicense;

    public UserResult(User user) {
        BeanUtils.copyProperties(user, this);
        for (Role role : user.getRoleSet()) {
            this.roleIds.add(role.getRoleId());
        }
    }
}
