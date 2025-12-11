package com.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reviewer_Expertise")
public class ReviewerExpertise {
    @EmbeddedId
    private ReviewerExpertiseId id;

    @Column(name = "Expertise_level")
    private Integer expertiseLevel;

    public ReviewerExpertiseId getId() {
        return id;
    }

    public void setId(ReviewerExpertiseId id) {
        this.id = id;
    }

    public Integer getExpertiseLevel() {
        return expertiseLevel;
    }

    public void setExpertiseLevel(Integer expertiseLevel) {
        this.expertiseLevel = expertiseLevel;
    }

}