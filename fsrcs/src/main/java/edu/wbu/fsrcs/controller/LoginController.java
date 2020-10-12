package edu.wbu.fsrcs.controller;

import edu.wbu.fsrcs.entity.*;
import edu.wbu.fsrcs.service.PermissionService;
import edu.wbu.fsrcs.service.RoleService;
import edu.wbu.fsrcs.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/fsrcs")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PermissionService permissionService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            User loginInfo = userService.queryUser(user.getUsername(), user.getPassword());
            String userRole = loginInfo.getUserFlag();
            List<Permission> permission = new LinkedList<>();
            // 1:普通用户,2：医师,3：管理员.
            String[] role = userRole.split(",");
            if ( role.length > 0) {
                for (int i = 0; i< role.length; i++) {
                    System.out.println(role[i]);
                    Role role1 = new Role();
                    role1.setRole(role[i]);
                    Role role2 = roleService.queryRoleIdByRole(role1);
                    // 通过角色Id拿到权限Id
                    String roleId = role2.getRoleId().toString();
                    List<Permission> permissionList = permissionService.queryPermissionId(roleId);
                    // 通过权限Id拿到权限
                    for (int j = 0; j<permissionList.size();j++){
                        permission.add(permissionService.queryPermission(permissionList.get(j).getPermissionId().toString()));
                    }
                }
            }
            Result result = new Result(ResultCode.SUCCESS);
            loginInfo.setData(permission);
            loginInfo.setToken(token);
            result.setData(loginInfo);
            return result;
        } catch (UnknownAccountException e) {
            return new Result(ResultCode.UnknownAccount);
        }
        catch (IncorrectCredentialsException e) {
            return new Result(ResultCode.USERNAMEORPASSWORDERROR);
        } catch (LockedAccountException e) {
            return new Result(ResultCode.DISABLED);
        } catch (AuthenticationException e) {
            return new Result(ResultCode.UNAUTHORISE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(ResultCode.FAIL);
    }

    @PostMapping("/logout")
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(ResultCode.SUCCESS);
    }
}
