package com.cms.service;

import com.cms.model.Submission;

import java.util.List;

public interface SubmissionService {

    Submission createSubmission(Submission submission);

    Submission updateSubmission(Long id, Submission submission);

    void deleteSubmission(Long id);

    Submission getSubmissionById(Long id);

    List<Submission> getAllSubmissions();

    List<Submission> getSubmissionsByConference(Long conferenceId);

    List<Submission> getSubmissionsByStatus(String status);

    List<Submission> searchSubmissionsByTitle(String keyword);
}
