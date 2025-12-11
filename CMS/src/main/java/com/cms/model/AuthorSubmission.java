package com.cms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Author_Submission")
public class AuthorSubmission {

    @EmbeddedId // 1. Use @EmbeddedId for the composite key
    private AuthorSubmissionId id;

    // 2. Map the User and Submission entities
    // This tells JPA that the "UserID" in the key is a foreign key
    @ManyToOne
    @MapsId("userId") // 3. Maps the "userId" field from AuthorSubmissionId
    @JoinColumn(name = "UserID")
    private User user;

    // This tells JPA that the "SubmissionID" in the key is a foreign key
    @ManyToOne
    @MapsId("submissionId") // 4. Maps the "submissionId" field from AuthorSubmissionId
    @JoinColumn(name = "SubmissionID")
    private Submission submission;

    @Column(name = "Is_primaryAuthor")
    private Boolean isPrimaryAuthor;


    // --- Getters and Setters ---

    public AuthorSubmissionId getId() {
        return id;
    }

    public void setId(AuthorSubmissionId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }

    public Boolean getPrimaryAuthor() {
        return isPrimaryAuthor;
    }

    public void setPrimaryAuthor(Boolean primaryAuthor) {
        isPrimaryAuthor = primaryAuthor;
    }
}