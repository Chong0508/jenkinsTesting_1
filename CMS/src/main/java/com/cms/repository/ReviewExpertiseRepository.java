package com.cms.repository;

import com.cms.model.ReviewerExpertise;
import com.cms.model.ReviewerExpertiseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewExpertiseRepository extends JpaRepository<ReviewerExpertise, ReviewerExpertiseId> {

    List<ReviewerExpertise> findById_ReviewerID(Long reviewerID);

    List<ReviewerExpertise> findById_TopicID(Long topicID);

    List<ReviewerExpertise> findById_TopicIDAndExpertiseLevelGreaterThanEqual(Long topicID, Integer level);

}
