package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.ReviewQualityRating;
import com.cms.repository.ReviewQualityRatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewQualityRatingServiceImpl implements ReviewQualityRatingService {

    private final ReviewQualityRatingRepository reviewQualityRatingRepository;

    public ReviewQualityRatingServiceImpl(ReviewQualityRatingRepository reviewQualityRatingRepository) {
        this.reviewQualityRatingRepository = reviewQualityRatingRepository;
    }

    @Override
    public List<ReviewQualityRating> getAllRatings() {
        return reviewQualityRatingRepository.findAll();
    }

    @Override
    public ReviewQualityRating getRatingById(Long id) {
        return reviewQualityRatingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ReviewQualityRating", "id", id));
    }

    @Override
    public ReviewQualityRating createRating(ReviewQualityRating rating) {
        return reviewQualityRatingRepository.save(rating);
    }

    @Override
    public ReviewQualityRating updateRating(Long id, ReviewQualityRating ratingDetails) {
        ReviewQualityRating existing = getRatingById(id);

        existing.setReviewID(ratingDetails.getReviewID());
        existing.setHelpfulnessScore(ratingDetails.getHelpfulnessScore());

        return reviewQualityRatingRepository.save(existing);
    }

    @Override
    public void deleteRating(Long id) {
        ReviewQualityRating existing = getRatingById(id);
        reviewQualityRatingRepository.delete(existing);
    }

    @Override
    public List<ReviewQualityRating> getRatingsByReview(Long reviewId) {
        return reviewQualityRatingRepository.findByReviewID_Id(reviewId);
    }

    @Override
    public List<ReviewQualityRating> getRatingsByScore(Integer score) {
        return reviewQualityRatingRepository.findByHelpfulnessScore(score);
    }

    @Override
    public List<ReviewQualityRating> getRatingsWithMinScore(Integer minScore) {
        return reviewQualityRatingRepository.findByHelpfulnessScoreGreaterThanEqual(minScore);
    }
}
