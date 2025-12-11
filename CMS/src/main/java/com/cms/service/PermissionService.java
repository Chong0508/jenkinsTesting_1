package com.cms.service;

import com.cms.model.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getAllPermissions();

    Permission getPermissionById(Long id);

    Permission getPermissionByName(String name);

    List<Permission> searchPermissionsByKeyword(String keyword);

    List<Permission> getPermissionsByModule(String module);

    Permission createPermission(Permission permission);

    Permission updatePermission(Long id, Permission permissionDetails);

    void deletePermission(Long id);
}
