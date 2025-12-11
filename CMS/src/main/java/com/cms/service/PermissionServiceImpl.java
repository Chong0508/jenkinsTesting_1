package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Permission;
import com.cms.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission", "id", id));
    }

    @Override
    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Permission", "name", name));
    }

    @Override
    public List<Permission> searchPermissionsByKeyword(String keyword) {
        return permissionRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Permission> getPermissionsByModule(String module) {
        return permissionRepository.findByModule(module);
    }

    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, Permission permissionDetails) {
        Permission existing = getPermissionById(id);

        existing.setName(permissionDetails.getName());
        existing.setModule(permissionDetails.getModule());

        return permissionRepository.save(existing);
    }

    @Override
    public void deletePermission(Long id) {
        Permission existing = getPermissionById(id);
        permissionRepository.delete(existing);
    }
}
