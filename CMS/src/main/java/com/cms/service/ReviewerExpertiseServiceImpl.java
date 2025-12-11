package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.ReviewerExpertise;
import com.cms.model.ReviewerExpertiseId;
import com.cms.repository.ReviewExpertiseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviewerExpertiseServiceImpl implements ReviewerExpertiseService {

    private final ReviewExpertiseRepository reviewExpertiseRepository;

    public ReviewerExpertiseServiceImpl(ReviewExpertiseRepository reviewExpertiseRepository) {
        this.reviewExpertiseRepository = reviewExpertiseRepository;
    }

    @Override
    public List<ReviewerExpertise> getAllReviewerExpertise() {
        return reviewExpertiseRepository.findAll();
    }

    @Override
    public ReviewerExpertise getById(ReviewerExpertiseId id) {
        return reviewExpertiseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("ReviewerExpertise", "id", id));
    }

    @Override
    public ReviewerExpertise createReviewerExpertise(ReviewerExpertise reviewerExpertise) {
        return reviewExpertiseRepository.save(reviewerExpertise);
    }

    @Override
    public ReviewerExpertise updateReviewerExpertise(ReviewerExpertiseId id,
                                                     ReviewerExpertise reviewerExpertiseDetails) {

        ReviewerExpertise existing = getById(id);

        existing.setExpertiseLevel(reviewerExpertiseDetails.getExpertiseLevel());
        // If you want to allow changing reviewer/topic themselves, you could also update the id here,
        // but usually composite PKs are treated as immutable after creation.

        return reviewExpertiseRepository.save(existing);
    }

    @Override
    public void deleteReviewerExpertise(ReviewerExpertiseId id) {
        ReviewerExpertise existing = getById(id);
        reviewExpertiseRepository.delete(existing);
    }

    @Override
    public List<ReviewerExpertise> getByReviewerId(Long reviewerId) {
        return reviewExpertiseRepository.findById_ReviewerID(reviewerId);
    }

    @Override
    public List<ReviewerExpertise> getByTopicId(Long topicId) {
        return reviewExpertiseRepository.findById_TopicID(topicId);
    }

    @Override
    public List<ReviewerExpertise> getByTopicIdWithMinLevel(Long topicId, Integer minLevel) {
        return reviewExpertiseRepository
                .findById_TopicIDAndExpertiseLevelGreaterThanEqual(topicId, minLevel);
    }
}
