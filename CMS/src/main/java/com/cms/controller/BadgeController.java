package com.cms.controller;

import com.cms.model.Badge;
import com.cms.service.BadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/badges")
public class BadgeController {

    private final BadgeService badgeService;

    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    // ------------------ GET ALL ------------------
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        return ResponseEntity.ok(badgeService.getAllBadges());
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable Long id) {
        return ResponseEntity.ok(badgeService.getBadgeById(id));
    }

    // ------------------ GET BY NAME ------------------
    @GetMapping("/name")
    public ResponseEntity<Badge> getBadgeByName(@RequestParam String name) {
        return ResponseEntity.ok(badgeService.getBadgeByName(name));
    }

    // ------------------ CREATE ------------------
    @PostMapping
    public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {
        return ResponseEntity.ok(badgeService.createBadge(badge));
    }

    // ------------------ UPDATE ------------------
    @PutMapping("/{id}")
    public ResponseEntity<Badge> updateBadge(
            @PathVariable Long id,
            @RequestBody Badge badgeDetails) {
        return ResponseEntity.ok(badgeService.updateBadge(id, badgeDetails));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBadge(@PathVariable Long id) {
        badgeService.deleteBadge(id);
        return ResponseEntity.ok("Badge with ID " + id + " deleted successfully.");
    }
}
