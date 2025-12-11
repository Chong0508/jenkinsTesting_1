package com.cms.repository;

import com.cms.model.AuthorSubmission;
import com.cms.model.AuthorSubmissionId; // 1. Import the composite ID
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorSubmissionRepository extends JpaRepository<AuthorSubmission, AuthorSubmissionId> {

    /*
     * Finds all author-submission links for a specific user.
     * This lets you get all submissions for one user.
     * Note: "User_Id" matches the 'user' field in AuthorSubmission
     * and the 'id' field in User.
     */
    List<AuthorSubmission> findByUser_Id(Long userId);

    /*
     * Finds all author-submission links for a specific submission.
     * This lets you get all authors for one paper.
     * Note: "Submission_Id" matches the 'submission' field in AuthorSubmission
     * and the 'id' field in Submission.
     */
    List<AuthorSubmission> findBySubmission_Id(Long submissionId);
}
