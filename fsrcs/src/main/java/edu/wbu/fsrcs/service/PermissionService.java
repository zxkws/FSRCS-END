package edu.wbu.fsrcs.service;

import edu.wbu.fsrcs.dao.PermissionDao;
import edu.wbu.fsrcs.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;

    public List<Permission> queryPermissionId(String roleId){
        return permissionDao.queryPermissionId(roleId);
    }

    public Permission queryPermission(String permissionId){
        return permissionDao.queryPermission(permissionId);
    }
}
