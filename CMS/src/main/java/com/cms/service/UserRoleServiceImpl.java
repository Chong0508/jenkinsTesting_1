package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.UserRole;
import com.cms.model.UserRoleId;
import com.cms.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole getUserRoleById(UserRoleId id) {
        return userRoleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserRole", "id", id));
    }

    @Override
    public UserRole assignRoleToUser(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void removeUserRole(UserRoleId id) {
        UserRole existing = getUserRoleById(id);
        userRoleRepository.delete(existing);
    }

    @Override
    public List<UserRole> getRolesByUser(Long userId) {
        return userRoleRepository.findByUserID_Id(userId);
    }

    @Override
    public List<UserRole> getUsersByRole(Long roleId) {
        return userRoleRepository.findByRoleID_Id(roleId);
    }

    @Override
    public UserRole getUserRole(Long userId, Long roleId) {
        return userRoleRepository.findByUserID_IdAndRoleID_Id(userId, roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("UserRole",
                                "userId/roleId", userId + "/" + roleId));
    }
}
