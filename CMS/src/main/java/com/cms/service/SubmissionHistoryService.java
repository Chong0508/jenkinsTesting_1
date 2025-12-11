package com.cms.service;

import com.cms.model.SubmissionHistory;

import java.util.List;

public interface SubmissionHistoryService {

    List<SubmissionHistory> getAllHistory();

    SubmissionHistory getHistoryById(Long id);

    SubmissionHistory createHistory(SubmissionHistory history);

    void deleteHistory(Long id);

    List<SubmissionHistory> getHistoryBySubmission(Long submissionId);

    List<SubmissionHistory> getHistoryByOldStatus(String oldStatus);

    List<SubmissionHistory> getHistoryByNewStatus(String newStatus);
}
