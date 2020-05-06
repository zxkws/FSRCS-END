package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String roleId;
    private String role;
    private String description;
    private Boolean available;
    private String users;
    private String permissions;
    private Set<User> userSet = new HashSet<User>();
    private Set<Permission> permissionSet = new HashSet<Permission>();
}
