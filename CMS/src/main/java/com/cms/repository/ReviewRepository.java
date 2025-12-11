package com.cms.repository;

import com.cms.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findBySubmissionId(Long submissionId);

    List<Review> findByReviewerId(Long reviewerId);

    List<Review> findByOverallScore(Integer overallScore);
    List<Review> findByConfidenceScore(Integer confidenceScore);

    List<Review> findByOverallScoreGreaterThanEqual(Integer score);
}
