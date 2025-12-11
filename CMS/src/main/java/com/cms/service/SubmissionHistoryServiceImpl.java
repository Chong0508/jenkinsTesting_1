package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.SubmissionHistory;
import com.cms.repository.SubmissionHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubmissionHistoryServiceImpl implements SubmissionHistoryService {

    private final SubmissionHistoryRepository submissionHistoryRepository;

    public SubmissionHistoryServiceImpl(SubmissionHistoryRepository submissionHistoryRepository) {
        this.submissionHistoryRepository = submissionHistoryRepository;
    }

    @Override
    public List<SubmissionHistory> getAllHistory() {
        return submissionHistoryRepository.findAll();
    }

    @Override
    public SubmissionHistory getHistoryById(Long id) {
        return submissionHistoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("SubmissionHistory", "id", id));
    }

    @Override
    public SubmissionHistory createHistory(SubmissionHistory history) {
        return submissionHistoryRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        if (!submissionHistoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("SubmissionHistory", "id", id);
        }
        submissionHistoryRepository.deleteById(id);
    }

    @Override
    public List<SubmissionHistory> getHistoryBySubmission(Long submissionId) {
        return submissionHistoryRepository.findBySubmissionID_Id(submissionId);
    }

    @Override
    public List<SubmissionHistory> getHistoryByOldStatus(String oldStatus) {
        return submissionHistoryRepository.findByOldStatus(oldStatus);
    }

    @Override
    public List<SubmissionHistory> getHistoryByNewStatus(String newStatus) {
        return submissionHistoryRepository.findByNewStatus(newStatus);
    }
}
