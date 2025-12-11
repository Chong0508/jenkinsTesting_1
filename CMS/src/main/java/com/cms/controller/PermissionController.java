package com.cms.controller;

import com.cms.model.Permission;
import com.cms.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@CrossOrigin(origins = "*") // allow frontend (e.g. React/Vue) to call this API
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // ---------- GET ALL ----------
    @GetMapping
    public ResponseEntity<List<Permission>> getAllPermissions() {
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    // ---------- GET BY ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermissionById(id));
    }

    // ---------- GET BY NAME (exact match) ----------
    @GetMapping("/by-name")
    public ResponseEntity<Permission> getPermissionByName(@RequestParam String name) {
        return ResponseEntity.ok(permissionService.getPermissionByName(name));
    }

    // ---------- SEARCH BY KEYWORD (contains, ignore case) ----------
    @GetMapping("/search")
    public ResponseEntity<List<Permission>> searchPermissionsByKeyword(@RequestParam String keyword) {
        return ResponseEntity.ok(permissionService.searchPermissionsByKeyword(keyword));
    }

    // ---------- GET BY MODULE ----------
    @GetMapping("/module")
    public ResponseEntity<List<Permission>> getPermissionsByModule(@RequestParam String module) {
        return ResponseEntity.ok(permissionService.getPermissionsByModule(module));
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission created = permissionService.createPermission(permission);
        return ResponseEntity.ok(created);
    }

    // ---------- UPDATE ----------
    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(
            @PathVariable Long id,
            @RequestBody Permission permissionDetails) {

        Permission updated = permissionService.updatePermission(id, permissionDetails);
        return ResponseEntity.ok(updated);
    }

    // ---------- DELETE ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok("Permission with id " + id + " deleted successfully.");
    }
}
