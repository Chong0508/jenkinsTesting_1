package com.cms.controller;

import com.cms.model.Userscore;
import com.cms.model.UserscoreId;
import com.cms.service.UserScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/userscore")
@CrossOrigin(origins = "*")
public class UserScoreController {
    private final UserScoreService service;

    public UserScoreController(UserScoreService service) {
        this.service = service;
    }

    // 1. GET All Scores
    @GetMapping
    public ResponseEntity<List<Userscore>> getAllScores() {
        return ResponseEntity.ok(service.getAllScores());
    }

    // 2. GET Score By ID
    @GetMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<Userscore> getScoreById(@PathVariable Long userId, @PathVariable Long badgeId) {
        UserscoreId id = new UserscoreId(userId, badgeId);
        return ResponseEntity.ok(service.getScoreById(id));
    }

    // 3. CREATE Score
    @PostMapping
    public ResponseEntity<Userscore> createOrUpdateScore(@RequestBody Userscore userscore) {
        return ResponseEntity.ok(service.createOrUpdateScore(userscore));
    }

    // 4. Delete Score
    @DeleteMapping("/user/{userId}/badge/{badgeId}")
    public ResponseEntity<String> deleteScore(@PathVariable Long userId, @PathVariable Long badgeId) {
        UserscoreId id = new UserscoreId(userId, badgeId);
        service.deleteScore(id);
        return ResponseEntity.ok("Score with badgeID " + badgeId + " and userID " + userId + " deleted successfully.");
    }

    // 5. GET Scores By userID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Userscore>> getScoresByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getScoresByUser(userId));
    }

    // 6. GET Scores By badgeID
    @GetMapping("/badge/{badgeId}")
    public ResponseEntity<List<Userscore>> getScoresByBadge(@PathVariable Long badgeId) {
        return ResponseEntity.ok(service.getScoresByBadge(badgeId));
    }

}