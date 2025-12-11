package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.AuthorSubmission;
import com.cms.model.AuthorSubmissionId;
import com.cms.model.Submission;
import com.cms.model.User;
import com.cms.repository.AuthorSubmissionRepository;
import com.cms.repository.SubmissionRepository;
import com.cms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorSubmissionServiceImpl implements AuthorSubmissionService {

    private final AuthorSubmissionRepository authorSubmissionRepository;
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;

    public AuthorSubmissionServiceImpl(AuthorSubmissionRepository authorSubmissionRepository,
                                       UserRepository userRepository,
                                       SubmissionRepository submissionRepository) {
        this.authorSubmissionRepository = authorSubmissionRepository;
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
    }

    @Override
    public List<AuthorSubmission> getAllAuthorsSubmission() {
        return authorSubmissionRepository.findAll();
    }

    @Override
    public List<AuthorSubmission> getAuthorsBySubmission(Long submissionId) {
        return authorSubmissionRepository.findBySubmission_Id(submissionId);
    }

    @Override
    public List<AuthorSubmission> getSubmissionsByUser(Long userId) {
        return authorSubmissionRepository.findByUser_Id(userId);
    }

    @Override
    public AuthorSubmission addAuthorToSubmission(Long userId, Long submissionId, Boolean isPrimary) {

        // ✅ use 3-arg constructor
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new ResourceNotFoundException("Submission", "id", submissionId));

        AuthorSubmissionId id = new AuthorSubmissionId(userId, submissionId);

        AuthorSubmission entity = new AuthorSubmission();
        entity.setId(id);
        entity.setUser(user);
        entity.setSubmission(submission);
        entity.setPrimaryAuthor(isPrimary); // ✅ correct method name

        return authorSubmissionRepository.save(entity);
    }

    @Override
    public AuthorSubmission updateAuthorSubmission(Long userId, Long submissionId, Boolean isPrimary) {
        AuthorSubmissionId id = new AuthorSubmissionId(userId, submissionId);
        AuthorSubmission entity = authorSubmissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuthorSubmission", "id", id));
        entity.setPrimaryAuthor(isPrimary);
        return authorSubmissionRepository.save(entity);
    }

    @Override
    public void removeAuthorFromSubmission(Long userId, Long submissionId) {
        AuthorSubmissionId id = new AuthorSubmissionId(userId, submissionId);

        // ✅ use 3-arg constructor, pass the id object
        AuthorSubmission authorSubmission = authorSubmissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AuthorSubmission", "id", id));

        authorSubmissionRepository.delete(authorSubmission);
    }
}
