package com.cms.repository;

import com.cms.model.UserRole;
import com.cms.model.UserRoleId; // 1. Import the composite ID class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUserID_Id(Long userId);

    List<UserRole> findByRoleID_Id(Long roleId);

    Optional<UserRole> findByUserID_IdAndRoleID_Id(Long userId, Long roleId);
}
