package com.cms.repository;

import com.cms.model.RolePermission;
import com.cms.model.RolePermissionId; // 1. Import the composite ID class
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId>{

    List<RolePermission> findByRoleID_Id(Long roleId);

    List<RolePermission> findByPermissionID_Id(Long permissionId);
}
