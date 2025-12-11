package com.cms.repository;

import com.cms.model.ReviewQualityRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewQualityRatingRepository extends JpaRepository<ReviewQualityRating, Long>{

    List<ReviewQualityRating> findByReviewID_Id(Long reviewId);

    List<ReviewQualityRating> findByHelpfulnessScore(Integer score);

    List<ReviewQualityRating> findByHelpfulnessScoreGreaterThanEqual(Integer score);
}
