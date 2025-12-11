package com.cms.service;

import com.cms.model.UserRole;
import com.cms.model.UserRoleId;

import java.util.List;

public interface UserRoleService {

    List<UserRole> getAllUserRoles();

    UserRole getUserRoleById(UserRoleId id);

    UserRole assignRoleToUser(UserRole userRole);

    void removeUserRole(UserRoleId id);

    List<UserRole> getRolesByUser(Long userId);

    List<UserRole> getUsersByRole(Long roleId);

    UserRole getUserRole(Long userId, Long roleId);
}
