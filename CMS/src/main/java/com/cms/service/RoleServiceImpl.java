package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Role;
import com.cms.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // ----------- GET ALL -----------
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // ----------- GET BY ID -----------
    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role", "id", id));
    }

    // ----------- CREATE -----------
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    // ----------- UPDATE -----------
    @Override
    public Role updateRole(Long id, Role roleDetails) {
        Role existing = getRoleById(id);

        existing.setName(roleDetails.getName());
        existing.setDescription(roleDetails.getDescription());
        existing.setParentRoleID(roleDetails.getParentRoleID()); // can be null

        return roleRepository.save(existing);
    }

    // ----------- DELETE -----------
    @Override
    public void deleteRole(Long id) {
        Role existing = getRoleById(id);
        roleRepository.delete(existing);
    }

    // ----------- FIND BY PARENT ROLE -----------
    @Override
    public List<Role> getRolesByParent(Long parentRoleId) {
        return roleRepository.findByParentRoleID_Id(parentRoleId);
    }

    // ----------- FIND ROOT ROLES (NO PARENT) -----------
    @Override
    public List<Role> getRootRoles() {
        return roleRepository.findByParentRoleIDIsNull();
    }
}

