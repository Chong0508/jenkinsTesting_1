package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Review;
import com.cms.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Review", "id", id));
    }

    @Override
    public Review createReview(Review review) {
        // Optional: validate that reviewerId and submissionId exist
        if (review.getReviewerId() == null) {
            throw new IllegalArgumentException("ReviewerID cannot be null");
        }
        if (review.getSubmissionId() == null) {
            throw new IllegalArgumentException("SubmissionID cannot be null");
        }

        return reviewRepository.save(review);
    }


    @Override
    public Review updateReview(Long id, Review reviewDetails) {
        Review existing = getReviewById(id);

        existing.setOverallScore(reviewDetails.getOverallScore());
        existing.setConfidenceScore(reviewDetails.getConfidenceScore());
        existing.setCommentToAuthor(reviewDetails.getCommentToAuthor());
        existing.setCommentsToPc(reviewDetails.getCommentsToPc());

        return reviewRepository.save(existing);
    }

    @Override
    public void deleteReview(Long id) {
        Review existing = getReviewById(id);
        reviewRepository.delete(existing);
    }

    @Override
    public List<Review> getReviewsBySubmissionId(Long submissionId) {
        return reviewRepository.findBySubmissionId(submissionId);
    }

    @Override
    public List<Review> getReviewsByReviewerId(Long reviewerId) {
        return reviewRepository.findByReviewerId(reviewerId);
    }

    @Override
    public List<Review> getReviewsByOverallScore(Integer score) {
        return reviewRepository.findByOverallScore(score);
    }

    @Override
    public List<Review> getReviewsByConfidenceScore(Integer score) {
        return reviewRepository.findByConfidenceScore(score);
    }

    @Override
    public List<Review> getReviewsWithMinOverallScore(Integer score) {
        return reviewRepository.findByOverallScoreGreaterThanEqual(score);
    }
}
