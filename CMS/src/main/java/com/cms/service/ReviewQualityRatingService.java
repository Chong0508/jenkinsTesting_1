package com.cms.service;

import com.cms.model.ReviewQualityRating;

import java.util.List;

public interface ReviewQualityRatingService {

    List<ReviewQualityRating> getAllRatings();

    ReviewQualityRating getRatingById(Long id);

    ReviewQualityRating createRating(ReviewQualityRating rating);

    ReviewQualityRating updateRating(Long id, ReviewQualityRating ratingDetails);

    void deleteRating(Long id);

    List<ReviewQualityRating> getRatingsByReview(Long reviewId);

    List<ReviewQualityRating> getRatingsByScore(Integer score);

    List<ReviewQualityRating> getRatingsWithMinScore(Integer minScore);
}
