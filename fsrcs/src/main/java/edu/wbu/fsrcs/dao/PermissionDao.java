package edu.wbu.fsrcs.dao;

import edu.wbu.fsrcs.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {
    List<Permission> queryPermissionId(@Param("roleId") String roleId);
    Permission queryPermission(@Param("permissionId") String permissionId);
}
