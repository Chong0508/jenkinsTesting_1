package com.cms.controller;

import com.cms.model.AuthorSubmission;
import com.cms.model.AuthorSubmissionId;
import com.cms.service.AuthorSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/author_submission")
public class AuthorSubmissionController {
    private final AuthorSubmissionService service;

    public AuthorSubmissionController(AuthorSubmissionService service) {
        this.service = service;
    }

    // 1. Read all data
    @GetMapping
    public ResponseEntity<List<AuthorSubmission>> getAllAuthorSubmission() {
        return ResponseEntity.ok(service.getAllAuthorsSubmission());
    }

    // 2. READ one author submission by userID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuthorSubmission>> getAuthorSubmissionByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getSubmissionsByUser(userId));
    }

    // 3. READ one author submission by submissionID
    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<AuthorSubmission>> getAuthorSubmissionBySubmissionId(@PathVariable Long submissionId) {
        return ResponseEntity.ok(service.getAuthorsBySubmission(submissionId));
    }

    // 4. CREATE NEW Author Submission
    @PostMapping
    public ResponseEntity<AuthorSubmission> createAuthorSubmission(@RequestBody AuthorSubmission newAuthorSubmission) {
        return ResponseEntity.ok(service.addAuthorToSubmission(
                newAuthorSubmission.getUser().getId(),
                newAuthorSubmission.getSubmission().getId(),
                newAuthorSubmission.getPrimaryAuthor()
        ));
    }

    // 5. UPDATE existing Author Submission
    @PutMapping("/user/{userId}/submission/{submissionId}")
    public ResponseEntity<AuthorSubmission> updateAuthorSubmission(@PathVariable Long userId, @PathVariable Long submissionId, @RequestBody AuthorSubmission updatedAuthorSubmission) {
        return ResponseEntity.ok(service.updateAuthorSubmission(userId, submissionId, updatedAuthorSubmission.getPrimaryAuthor()));
    }

    // 6. DELETE an author submission
    @DeleteMapping("/user/{userId}/submission/{submissionId}")
    public ResponseEntity<String> deleteAuthorSubmission(@PathVariable Long userId, @PathVariable Long submissionId) {
        service.removeAuthorFromSubmission(userId, submissionId);
        return ResponseEntity.ok("Author Submission with userID " + userId + " and submissionID " + submissionId +" deleted successfully.");
    }
}