package edu.wbu.fsrcs.config;

import edu.wbu.fsrcs.entity.Permission;
import edu.wbu.fsrcs.entity.Role;
import edu.wbu.fsrcs.entity.User;
import edu.wbu.fsrcs.service.PermissionService;
import edu.wbu.fsrcs.service.RoleService;
import edu.wbu.fsrcs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User userInfo = userService.queryUserByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        //账户冻结
        if (userInfo.getAccountStatus().equals("00")) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUsername(),
                userInfo.getPassword(),
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username  = (String) principals.getPrimaryPrincipal();
        User userInfo = userService.queryUserByUsername(username);
        String userRole = userInfo.getUserFlag();
        // 1:普通用户,2：医师,3：管理员.
        String[] role = userRole.split(",");
        if ( role.length > 0) {
            for (int i = 0; i< role.length; i++) {
                Role role1 = new Role();
                role1.setRole(role[i]);
                authorizationInfo.addRole(role[i]);
                Role role2 = roleService.queryRoleIdByRole(role1);
                // 通过角色Id拿到权限Id
                String roleId = role2.getRoleId().toString();
                List<Permission> permissionList = permissionService.queryPermissionId(roleId);
                // 通过权限Id拿到权限
                for (int j = 0; j<permissionList.size();j++){
                    authorizationInfo.addStringPermission(permissionService.queryPermission(permissionList.get(j).getPermissionId().toString()).getPermissionName());
                }
            }
        }
        return authorizationInfo;
    }
}
