package com.cms.service;

import com.cms.model.Submission;
import com.cms.model.Conference;
import com.cms.repository.SubmissionRepository;
import com.cms.repository.ConferenceRepository;
import com.cms.service.SubmissionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ConferenceRepository conferenceRepository;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, ConferenceRepository conferenceRepository) {
        this.submissionRepository = submissionRepository;
        this.conferenceRepository = conferenceRepository;
    }

    @Override
    public Submission createSubmission(Submission submission) {
        // Check if conference is provided
        if (submission.getConferenceID() == null || submission.getConferenceID().getId() == null) {
            throw new RuntimeException("Conference ID is required");
        }

        // Fetch the actual Conference entity from DB
        Conference conference = conferenceRepository.findById(submission.getConferenceID().getId())
                .orElseThrow(() -> new RuntimeException("Conference not found"));

        submission.setConferenceID(conference);

        return submissionRepository.save(submission);
    }

    @Override
    public Submission updateSubmission(Long id, Submission submissionDetails) {
        Submission existing = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + id));

        existing.setConferenceID(submissionDetails.getConferenceID());
        existing.setTitle(submissionDetails.getTitle());
        existing.setAbstractField(submissionDetails.getAbstractField());
        existing.setFileUrl(submissionDetails.getFileUrl());
        existing.setStatus(submissionDetails.getStatus());

        return submissionRepository.save(existing);
    }

    @Override
    public void deleteSubmission(Long id) {
        submissionRepository.deleteById(id);
    }

    @Override
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission not found with ID: " + id));
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByConference(Long conferenceId) {
        return submissionRepository.findByConferenceID_Id(conferenceId);
    }

    @Override
    public List<Submission> getSubmissionsByStatus(String status) {
        return submissionRepository.findByStatus(status);
    }

    @Override
    public List<Submission> searchSubmissionsByTitle(String keyword) {
        return submissionRepository.findByTitleContaining(keyword);
    }
}
