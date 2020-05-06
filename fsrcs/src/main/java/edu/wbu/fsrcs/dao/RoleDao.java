package edu.wbu.fsrcs.dao;

import edu.wbu.fsrcs.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> queryAllRole();
    Role queryRoleIdByRole(Role role);
    int add(Role role);
    int update(Role role);
    int delete(Role role);
    void deleteRoleAndPermissions(Role role);
    void insertRoleAndPermissions(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
