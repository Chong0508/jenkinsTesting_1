package com.cms.controller;

import com.cms.model.Review;
import com.cms.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    // 1. GET All Reviews
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        return ResponseEntity.ok(service.getAllReviews());
    }

    // 2. GET Review by ReviewID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReviewById(id));
    }

    // 3. CREATE Review
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(service.createReview(review));
    }

    // 4. UPDATE Review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review reviewDetails) {
        return ResponseEntity.ok(service.updateReview(id, reviewDetails));
    }

    // 5. DELETE Review
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        service.deleteReview(id);
        return ResponseEntity.ok("Review with reviewID " + id + " deleted successfully.");
    }

    // 6. GET Review By SubmissionID
    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<Review>> getReviewsBySubmissionId(@PathVariable Long submissionId) {
        return ResponseEntity.ok(service.getReviewsBySubmissionId(submissionId));
    }

    // 7. GET Review By ReviewerID
    @GetMapping("/reviewer/{reviewerId}")
    public ResponseEntity<List<Review>> getReviewsByReviewerId(@PathVariable Long reviewerId) {
        return ResponseEntity.ok(service.getReviewsByReviewerId(reviewerId));
    }

    // 8. GET Reviews By Overall Score
    @GetMapping("/overallScore")
    public ResponseEntity<List<Review>> getReviewsByOverallScore(@RequestParam Integer score) {
        return ResponseEntity.ok(service.getReviewsByOverallScore(score));
    }

    // 9. GET Reviews By Confidence Score
    @GetMapping("/confidenceScore")
    public ResponseEntity<List<Review>> getReviewsByConfidenceScore(@RequestParam Integer score) {
        return ResponseEntity.ok(service.getReviewsByConfidenceScore(score));
    }

    // 10. GET Reviews By Min Overall Score
    @GetMapping("/minOverallScore")
    public ResponseEntity<List<Review>> getReviewsWithMinOverallScore(@RequestParam Integer score) {
        return ResponseEntity.ok(service.getReviewsWithMinOverallScore(score));
    }
}