package edu.wbu.fsrcs.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Setter
@Getter
public class ProfileResult implements Serializable {
    private String userId;
    private String username;
    private String nickname;
    private Map<String, Object> roles = new HashMap<>();

    /**
     * @param user
     */
    public ProfileResult(User user, List<Permission> list) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickname = user.getNickName();
        Set<String> menus = new HashSet<>();
        Set<String> apis = new HashSet<>();

        for (Permission perm : list) {
            String code = perm.getPermissionId().toString();
            String url = perm.getPermissionName();
                menus.add(code);
                apis.add(url);
            }
        this.roles.put("menus", menus);
        this.roles.put("apis", apis);
    }


    public ProfileResult(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickname = user.getNickName();
        Set<Role> roles = user.getRoleSet();
        Set<String> menus = new HashSet<>();
        Set<String> apis = new HashSet<>();
        for (Role role : roles) {
            Set<Permission> perms = role.getPermissionSet();
            for (Permission perm : perms) {
                String code = perm.getPermissionId().toString();
                String url = perm.getPermissionName();
                menus.add(code);
                apis.add(url);
            }
        }

        this.roles.put("menus", menus);
        this.roles.put("apis", apis);
    }
}
