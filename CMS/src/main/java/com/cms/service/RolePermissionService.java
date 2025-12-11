package com.cms.service;

import com.cms.model.RolePermission;

import java.util.List;

public interface RolePermissionService {

    List<RolePermission> getByRole(Long roleId);

    List<RolePermission> getByPermission(Long permissionId);

    RolePermission addPermissionToRole(Long roleId, Long permissionId);

    void removePermissionFromRole(Long roleId, Long permissionId);
}
