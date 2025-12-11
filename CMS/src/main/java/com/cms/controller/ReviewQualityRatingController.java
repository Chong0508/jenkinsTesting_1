package com.cms.controller;

import com.cms.model.ReviewQualityRating;
import com.cms.service.ReviewQualityRatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/review_quality_rating")
@CrossOrigin(origins = "*")
public class ReviewQualityRatingController {
    private final ReviewQualityRatingService service;

    public ReviewQualityRatingController(ReviewQualityRatingService service) {
        this.service = service;
    }

    // 1. GET All Review Quality Rating
    @GetMapping
    public ResponseEntity<List<ReviewQualityRating>> getAllRatings() {
        return ResponseEntity.ok(service.getAllRatings());
    }

    // 2. GET By id
    @GetMapping("/{id}")
    public ResponseEntity<ReviewQualityRating> getRatingById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getRatingById(id));
    }

    // 3. CREATE Review Quality Rating
    @PostMapping
    public ResponseEntity<ReviewQualityRating> createRating(@RequestBody ReviewQualityRating rating) {
        return ResponseEntity.ok(service.createRating(rating));
    }

    // 4. UPDATE Review Quality Rating
    @PutMapping("/{id}")
    public ResponseEntity<ReviewQualityRating> updateRating(@PathVariable Long id, @RequestBody ReviewQualityRating ratingDetails) {
        return ResponseEntity.ok(service.updateRating(id, ratingDetails));
    }

    // 5. DELETE Review Quality Rating
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        service.deleteRating(id);
        return ResponseEntity.ok("Review Quality Rating with ratingID " + id + " deleted successfully.");
    }

    // 6. GET Review Quality Rating By ReviewID
    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<ReviewQualityRating>> getRatingsByReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(service.getRatingsByReview(reviewId));
    }

    // 7. GET Review Quality Rating By Score
    @GetMapping("/score")
    public ResponseEntity<List<ReviewQualityRating>> getRatingsByScore(@RequestParam Integer score) {
        return ResponseEntity.ok(service.getRatingsByScore(score));
    }

    // 8. GET Review Quality Rating By Min Score
    @GetMapping("/min-score")
    public ResponseEntity<List<ReviewQualityRating>> getRatingsWithMinScore(@RequestParam Integer minScore) {
        return ResponseEntity.ok(service.getRatingsWithMinScore(minScore));
    }

}