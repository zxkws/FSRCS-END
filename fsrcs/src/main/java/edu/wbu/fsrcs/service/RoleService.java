package edu.wbu.fsrcs.service;

import edu.wbu.fsrcs.dao.RoleDao;
import edu.wbu.fsrcs.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;

    /**
     * 通过角色查询角色Id
     * @param role
     * @return
     */
    public Role queryRoleIdByRole(Role role){
        return roleDao.queryRoleIdByRole(role);
    }

    public List<Role> queryAllRole(){
        return roleDao.queryAllRole();
    }

    public void deleteRoleAndPermissions(Role role){
        roleDao.deleteRoleAndPermissions(role);
    }

    public void insertRoleAndPermissions(String roleId,String permissionId){
        roleDao.insertRoleAndPermissions(roleId,permissionId);
    }
}
