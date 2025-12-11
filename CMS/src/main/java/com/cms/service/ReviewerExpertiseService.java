package com.cms.service;

import com.cms.model.ReviewerExpertise;
import com.cms.model.ReviewerExpertiseId;

import java.util.List;

public interface ReviewerExpertiseService {

    List<ReviewerExpertise> getAllReviewerExpertise();

    ReviewerExpertise getById(ReviewerExpertiseId id);

    ReviewerExpertise createReviewerExpertise(ReviewerExpertise reviewerExpertise);

    ReviewerExpertise updateReviewerExpertise(ReviewerExpertiseId id, ReviewerExpertise reviewerExpertiseDetails);

    void deleteReviewerExpertise(ReviewerExpertiseId id);

    List<ReviewerExpertise> getByReviewerId(Long reviewerId);

    List<ReviewerExpertise> getByTopicId(Long topicId);

    List<ReviewerExpertise> getByTopicIdWithMinLevel(Long topicId, Integer minLevel);
}
