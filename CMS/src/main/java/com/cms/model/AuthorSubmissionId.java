package com.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable // States this class can be embedded in another entity
public class AuthorSubmissionId implements Serializable {

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "SubmissionID")
    private Long submissionId;

    // --- Constructors, Getters/Setters, hashCode/equals ---

    // JPA requires a no-arg constructor
    public AuthorSubmissionId() {
    }

    public AuthorSubmissionId(Long userId, Long submissionId) {
        this.userId = userId;
        this.submissionId = submissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    // JPA requires hashCode() and equals() for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorSubmissionId that = (AuthorSubmissionId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(submissionId, that.submissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, submissionId);
    }
}
