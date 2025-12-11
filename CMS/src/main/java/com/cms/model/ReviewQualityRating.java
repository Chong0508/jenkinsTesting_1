package com.cms.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Review_Quality_Rating")
public class ReviewQualityRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RatingID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ReviewID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Review reviewID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RaterUserID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User raterUserID;

    @Column(name = "Helpfulness_score")
    private Integer helpfulnessScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Review getReviewID() {
        return reviewID;
    }

    public void setReviewID(Review reviewID) {
        this.reviewID = reviewID;
    }

    public User getRaterUserID() {
        return raterUserID;
    }

    public void setRaterUserID(User raterUserID) {
        this.raterUserID = raterUserID;
    }

    public Integer getHelpfulnessScore() {
        return helpfulnessScore;
    }

    public void setHelpfulnessScore(Integer helpfulnessScore) {
        this.helpfulnessScore = helpfulnessScore;
    }

}