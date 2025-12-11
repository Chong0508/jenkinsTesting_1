package com.cms.controller;

import com.cms.model.Userbadge;
import com.cms.model.UserbadgeId;
import com.cms.service.UserBadgeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user_badge")
@CrossOrigin(origins = "*")
public class UserBadgeController {
    private final UserBadgeService service;
    public UserBadgeController(UserBadgeService service) {
        this.service = service;
    }

    // 1. GET All User Badge
    @GetMapping
    public ResponseEntity<List<Userbadge>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // 2. GET User Badge By ID
    @GetMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<Userbadge> getById(@PathVariable Long userId, @PathVariable Long badgeId) {
        UserbadgeId id = new UserbadgeId(userId, badgeId);
        return ResponseEntity.ok(service.getById(id));
    }

    // 3. ADD Badge To User
    @PostMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<Userbadge> addBadgeToUser(@PathVariable Long userId, @PathVariable Long badgeId, @RequestBody Userbadge userbadgeData) {
        return ResponseEntity.ok(service.addBadgeToUser(userId, badgeId, userbadgeData));
    }

    // 4. UPDATE User Badge
    @PutMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<Userbadge> updateUserBadge(
            @PathVariable Long userId,
            @PathVariable Long badgeId,
            @RequestBody Userbadge userbadgeData) {

        // Create composite ID
        UserbadgeId id = new UserbadgeId(userId, badgeId);

        // Call service
        Userbadge updated = service.updateUserBadge(id, userbadgeData);

        return ResponseEntity.ok(updated);
    }

    // 5. REMOVE / DELETE
    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<String> removeBadgeFromUser(@PathVariable Long userId, @PathVariable Long badgeId) {
        service.removeBadgeFromUser(userId, badgeId);
        return ResponseEntity.ok("Badge with badgeID " + badgeId + " from userID " + userId + " deleted successfully.");
    }

    // 6. GET Badges For User
    @GetMapping("/user/{userId}/badges")
    public ResponseEntity<List<Userbadge>> getBadgesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getBadgesForUser(userId));
    }

    // 7. GET Badges For User Ordered
    @GetMapping("/user/{userId}/badges/ordered")
    public ResponseEntity<List<Userbadge>> getBadgesForUserOrdered(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getBadgesForUserOrdered(userId));
    }

    // 8. GET Users For Badge
    @GetMapping("/badge/{badgeId}/users")
    public ResponseEntity<List<Userbadge>> getUsersForBadge(@PathVariable Long badgeId) {
        return ResponseEntity.ok(service.getUsersForBadge(badgeId));
    }

}