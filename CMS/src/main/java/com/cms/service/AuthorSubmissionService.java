package com.cms.service;

import com.cms.model.AuthorSubmission;

import java.util.List;


public interface AuthorSubmissionService {

    List<AuthorSubmission> getAllAuthorsSubmission();
    List<AuthorSubmission> getAuthorsBySubmission(Long submissionId);

    List<AuthorSubmission> getSubmissionsByUser(Long userId);

    AuthorSubmission addAuthorToSubmission(Long userId, Long submissionId, Boolean isPrimary);

    AuthorSubmission updateAuthorSubmission(Long userId, Long submissionId, Boolean isPrimary);

    void removeAuthorFromSubmission(Long userId, Long submissionId);
}
