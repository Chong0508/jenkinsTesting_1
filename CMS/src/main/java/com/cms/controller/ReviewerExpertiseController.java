package com.cms.controller;

import com.cms.model.ReviewerExpertise;
import com.cms.model.ReviewerExpertiseId;
import com.cms.service.ReviewerExpertiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviewer-expertise")
@CrossOrigin(origins = "*")
public class ReviewerExpertiseController {

    private final ReviewerExpertiseService reviewerExpertiseService;

    public ReviewerExpertiseController(ReviewerExpertiseService reviewerExpertiseService) {
        this.reviewerExpertiseService = reviewerExpertiseService;
    }

    // ---------- GET ALL ----------
    @GetMapping
    public ResponseEntity<List<ReviewerExpertise>> getAll() {
        return ResponseEntity.ok(reviewerExpertiseService.getAllReviewerExpertise());
    }

    // ---------- GET ONE BY COMPOSITE ID ----------
    @GetMapping("/reviewer-topic/{reviewerId}/{topicId}")
    public ResponseEntity<ReviewerExpertise> getById(
            @PathVariable Long reviewerId,
            @PathVariable Long topicId) {

        ReviewerExpertiseId id = new ReviewerExpertiseId(reviewerId, topicId);
        return ResponseEntity.ok(reviewerExpertiseService.getById(id));
    }

    // ---------- GET BY REVIEWER ----------
    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<List<ReviewerExpertise>> getByReviewer(
            @PathVariable Long reviewerId) {

        return ResponseEntity.ok(reviewerExpertiseService.getByReviewerId(reviewerId));
    }

    // ---------- GET BY TOPIC ----------
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<ReviewerExpertise>> getByTopic(
            @PathVariable Long topicId) {

        return ResponseEntity.ok(reviewerExpertiseService.getByTopicId(topicId));
    }

    // ---------- GET BY TOPIC WITH MIN LEVEL ----------
    @GetMapping("/topic/{topicId}/min-level")
    public ResponseEntity<List<ReviewerExpertise>> getByTopicWithMinLevel(
            @PathVariable Long topicId,
            @RequestParam("level") Integer minLevel) {

        return ResponseEntity.ok(
                reviewerExpertiseService.getByTopicIdWithMinLevel(topicId, minLevel)
        );
    }

    // ---------- CREATE ----------
    @PostMapping
    public ResponseEntity<ReviewerExpertise> create(@RequestBody ReviewerExpertise reviewerExpertise) {
        ReviewerExpertise created = reviewerExpertiseService.createReviewerExpertise(reviewerExpertise);
        return ResponseEntity.ok(created);
    }

    // ---------- UPDATE EXPERTISE LEVEL ----------
    @PutMapping("/reviewer-topic/{reviewerId}/{topicId}")
    public ResponseEntity<ReviewerExpertise> update(
            @PathVariable Long reviewerId,
            @PathVariable Long topicId,
            @RequestBody ReviewerExpertise reviewerExpertiseDetails) {

        ReviewerExpertiseId id = new ReviewerExpertiseId(reviewerId, topicId);
        ReviewerExpertise updated =
                reviewerExpertiseService.updateReviewerExpertise(id, reviewerExpertiseDetails);
        return ResponseEntity.ok(updated);
    }

    // ---------- DELETE ----------
    @DeleteMapping("/reviewer-topic/{reviewerId}/{topicId}")
    public ResponseEntity<String> delete(
            @PathVariable Long reviewerId,
            @PathVariable Long topicId) {
        ReviewerExpertiseId id = new ReviewerExpertiseId(reviewerId, topicId);
        reviewerExpertiseService.deleteReviewerExpertise(id);
        return ResponseEntity.ok("Reviewer expertise with reviewerID " + reviewerId + " and topicID "+ topicId + " deleted successfully.");
    }
}
