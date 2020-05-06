package edu.wbu.fsrcs.controller;

import com.sun.xml.internal.ws.api.message.HeaderList;
import edu.wbu.fsrcs.entity.Permission;
import edu.wbu.fsrcs.entity.Result;
import edu.wbu.fsrcs.entity.ResultCode;
import edu.wbu.fsrcs.entity.Role;
import edu.wbu.fsrcs.service.PermissionService;
import edu.wbu.fsrcs.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/fsrcs/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @GetMapping("/queryAll")
    public Result queryRole(){
        List<Role> roles = roleService.queryAllRole();
        for (Role role:roles) {
            List permissionStr = new LinkedList();
            String roleId = role.getRoleId();
            List<Permission> permissions = permissionService.queryPermissionId(roleId);
            for (Permission permission:permissions) {
                String permissionId = permission.getPermissionId().toString();
                Permission pers = permissionService.queryPermission(permissionId);
                permissionStr.add(pers.getPermissionName());
            }
            role.setPermissions(permissionStr.toString());
        }
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(roles);
        return result;
    }
    @PostMapping("/update")
    public Result updateRole(@RequestBody Role role){
        String roleId = role.getRoleId().toString();
        roleService.deleteRoleAndPermissions(role);
        String[] permissions = role.getPermissions().split(",");
        for (int i = 0; i< permissions.length;i++){
            roleService.insertRoleAndPermissions(roleId,permissions[i]);
        }
        return new Result(ResultCode.SUCCESS);
    }
}
