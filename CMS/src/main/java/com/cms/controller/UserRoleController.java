package com.cms.controller;

import com.cms.model.UserRole;
import com.cms.model.UserRoleId;
import com.cms.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user_role")
@CrossOrigin(origins = "*")
public class UserRoleController {
    private final UserRoleService service;
    public UserRoleController(UserRoleService service) {
        this.service = service;
    }

    // 1. GET All User Roles
    @GetMapping
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        return ResponseEntity.ok(service.getAllUserRoles());
    }

    // 2. GET User Role by compositeID
    @GetMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<UserRole> getUserRoleById(@PathVariable Long userId, @PathVariable Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        return ResponseEntity.ok(service.getUserRoleById(id));
    }

    // 3. ADD Role To User
    @PostMapping
    public ResponseEntity<UserRole> assignRoleToUser(@RequestBody UserRole userRole) {
        return ResponseEntity.ok(service.assignRoleToUser(userRole));
    }

    // 4. REMOVE User Role
    @DeleteMapping("/user/{userId}/role/{roleId}")
    public ResponseEntity<String> removeUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        service.removeUserRole(id);
        return ResponseEntity.ok("User Role with userID " + userId + " and roleID " + roleId + " deleted successfully.");
    }

    // 5. GET Roles By userID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRole>> getRolesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getRolesByUser(userId));
    }

    // 6. GET Users By Role
    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<UserRole>> getUsersByRole(@PathVariable Long roleId) {
        return ResponseEntity.ok(service.getUsersByRole(roleId));
    }

}