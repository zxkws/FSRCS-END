package edu.wbu.fsrcs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    private String permissionId;
    private String permissionName;
    private String url;
    private Boolean available;
    private String roles;
    private Set<Role> roleSet = new HashSet<Role>();
}
