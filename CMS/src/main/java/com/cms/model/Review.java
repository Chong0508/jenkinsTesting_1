package com.cms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReviewID", nullable = false)
    private Long id;

    @Column(name = "ReviewerID")
    private Long reviewerId;

    @Column(name = "SubmissionID")
    private Long submissionId;

    @Column(name = "Overall_score")
    private Integer overallScore;

    @Column(name = "Confidence_score")
    private Integer confidenceScore;

    @Lob
    @Column(name = "Comment_to_Author")
    private String commentToAuthor;

    @Lob
    @Column(name = "Comments_to_PC")
    private String commentsToPc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReviewerId() { return reviewerId; }
    public void setReviewerId(Long reviewerId) { this.reviewerId = reviewerId; }

    public Long getSubmissionId() { return submissionId; }
    public void setSubmissionId(Long submissionId) { this.submissionId = submissionId; }

    public Integer getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Integer overallScore) {
        this.overallScore = overallScore;
    }

    public Integer getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Integer confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getCommentToAuthor() {
        return commentToAuthor;
    }

    public void setCommentToAuthor(String commentToAuthor) {
        this.commentToAuthor = commentToAuthor;
    }

    public String getCommentsToPc() {
        return commentsToPc;
    }

    public void setCommentsToPc(String commentsToPc) {
        this.commentsToPc = commentsToPc;
    }

}