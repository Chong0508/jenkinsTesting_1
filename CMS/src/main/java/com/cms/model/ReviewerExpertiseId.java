package com.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReviewerExpertiseId implements Serializable {
    private static final long serialVersionUID = -1709687407151361850L;

    @Column(name = "ReviewerID", nullable = false)
    private Long reviewerID;

    @Column(name = "TopicID", nullable = false)
    private Long topicID;

    // 🔥 ADD THIS CONSTRUCTOR (Fixes your error)
    public ReviewerExpertiseId(Long reviewerID, Long topicID) {
        this.reviewerID = reviewerID;
        this.topicID = topicID;
    }

    // JPA requires a no-arg constructor
    public ReviewerExpertiseId() {}

    public Long getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(Long reviewerID) {
        this.reviewerID = reviewerID;
    }

    public Long getTopicID() {
        return topicID;
    }

    public void setTopicID(Long topicID) {
        this.topicID = topicID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ReviewerExpertiseId entity = (ReviewerExpertiseId) o;
        return Objects.equals(this.topicID, entity.topicID) &&
                Objects.equals(this.reviewerID, entity.reviewerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicID, reviewerID);
    }
}
