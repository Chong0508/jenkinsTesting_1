package com.cms.service;

import com.cms.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role createRole(Role role);

    Role updateRole(Long id, Role roleDetails);

    void deleteRole(Long id);

    List<Role> getRolesByParent(Long parentRoleId);

    List<Role> getRootRoles();
}
