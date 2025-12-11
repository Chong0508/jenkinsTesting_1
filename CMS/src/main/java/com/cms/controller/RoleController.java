package com.cms.controller;

import com.cms.model.Role;
import com.cms.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role")
@CrossOrigin(origins = "*")
public class RoleController {
    private final RoleService service;
    public RoleController(RoleService service) {
        this.service = service;
    }

    // 1. GET All Roles
    @GetMapping("/role")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(service.getAllRoles());
    }

    // 2. GET Role By ID
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRoleById(id));
    }

    // 3. CREATE Role
    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return ResponseEntity.ok(service.createRole(role));
    }

    // 4. UPDATE Role
    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        return ResponseEntity.ok(service.updateRole(id, roleDetails));
    }

    // 5. DELETE Role
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id) {
        service.deleteRole(id);
        return ResponseEntity.ok("Role with roleID " + id + " deleted successfully.");
    }

    // 6. GET Roles By ParentRoleID
    @GetMapping("/parent-role/{parentRoleId}")
    public ResponseEntity<List<Role>> getRolesByParent(@PathVariable Long parentRoleId) {
        return ResponseEntity.ok(service.getRolesByParent(parentRoleId));
    }

    // 7. GET Root Roles
    @GetMapping("/role/root")
    public ResponseEntity<List<Role>> getRootRoles() {
        return ResponseEntity.ok(service.getRootRoles());
    }
}