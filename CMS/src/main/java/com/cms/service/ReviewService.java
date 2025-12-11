package com.cms.service;

import com.cms.model.Review;
import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews();

    Review getReviewById(Long id);

    Review createReview(Review review);

    Review updateReview(Long id, Review reviewDetails);

    void deleteReview(Long id);

    List<Review> getReviewsBySubmissionId(Long submissionId);
    List<Review> getReviewsByReviewerId(Long reviewerId);

    List<Review> getReviewsByOverallScore(Integer score);

    List<Review> getReviewsByConfidenceScore(Integer score);

    List<Review> getReviewsWithMinOverallScore(Integer score);
}
