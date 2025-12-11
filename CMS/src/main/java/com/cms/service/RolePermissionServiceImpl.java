package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Permission;
import com.cms.model.Role;
import com.cms.model.RolePermission;
import com.cms.model.RolePermissionId;
import com.cms.repository.PermissionRepository;
import com.cms.repository.RolePermissionRepository;
import com.cms.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RolePermissionServiceImpl(RolePermissionRepository rolePermissionRepository,
                                     RoleRepository roleRepository,
                                     PermissionRepository permissionRepository) {
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<RolePermission> getByRole(Long roleId) {
        return rolePermissionRepository.findByRoleID_Id(roleId);
    }

    @Override
    public List<RolePermission> getByPermission(Long permissionId) {
        return rolePermissionRepository.findByPermissionID_Id(permissionId);
    }

    @Override
    public RolePermission addPermissionToRole(Long roleId, Long permissionId) {
        // Load Role
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role", "id", roleId));

        // Load Permission
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission", "id", permissionId));

        // Build composite key
        RolePermissionId id = new RolePermissionId();
        id.setRoleID(roleId);
        id.setPermissionID(permissionId);

        // Create junction entity
        RolePermission rp = new RolePermission();
        rp.setId(id);
        rp.setRoleID(role);
        rp.setPermissionID(permission);

        return rolePermissionRepository.save(rp);
    }

    @Override
    public void removePermissionFromRole(Long roleId, Long permissionId) {
        RolePermissionId id = new RolePermissionId();
        id.setRoleID(roleId);
        id.setPermissionID(permissionId);

        RolePermission existing = rolePermissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RolePermission", "id", id));

        rolePermissionRepository.delete(existing);
    }
}
