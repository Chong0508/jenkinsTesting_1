package com.cms.controller;

import com.cms.model.RolePermission;
import com.cms.model.RolePermissionId;
import com.cms.service.RolePermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role_permission")
@CrossOrigin(origins = "*")
public class RolePermissionController {
    private final RolePermissionService service;
    public RolePermissionController(RolePermissionService service) {
        this.service = service;
    }

    // 1. GET By roleID
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RolePermission>> getByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(service.getByRole(roleId));
    }

    // 2. GET By permissionID
    @GetMapping("/permission/{permissionId}")
    public ResponseEntity<List<RolePermission>> getByPermission(@PathVariable Long permissionId) {
        return ResponseEntity.ok(service.getByPermission(permissionId));
    }

    // 3. ADD / CREATE
    @PostMapping("/role/{roleId}/permission/{permissionId}")
    public ResponseEntity<RolePermission> addPermissionToRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        return ResponseEntity.ok(service.addPermissionToRole(roleId, permissionId));
    }

    // 4. DELETE
    @DeleteMapping("/role/{roleId}/permission/{permissionId}")
    public ResponseEntity<String> removePermissionFromRole(@PathVariable Long roleId, @PathVariable Long permissionId) {
        service.removePermissionFromRole(roleId, permissionId);
        return ResponseEntity.ok("Permission with permissionID " + permissionId + " and roleID " + roleId +" deleted successfully.");
    }
}