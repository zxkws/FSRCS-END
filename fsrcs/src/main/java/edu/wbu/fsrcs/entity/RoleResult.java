package edu.wbu.fsrcs.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RoleResult implements Serializable {
    private Long roleId;
    private String role;
    private String description;
    private Boolean available;

    private List<String> permIds = new ArrayList<>();

    public RoleResult(Role role) {
        BeanUtils.copyProperties(role, this);
        for (Permission perm : role.getPermissionSet()) {
            this.permIds.add(perm.getPermissionId());
        }
    }
}
