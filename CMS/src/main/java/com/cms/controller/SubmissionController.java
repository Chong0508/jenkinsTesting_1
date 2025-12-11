package com.cms.controller;

import com.cms.model.Submission;
import com.cms.service.SubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submissions")
@CrossOrigin(origins = "*")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    // 1. GET All Submission
    @GetMapping
    public ResponseEntity<List<Submission>> getAll() {
        return ResponseEntity.ok(submissionService.getAllSubmissions());
    }

    // 2. GET Submission By SubmissionID
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getById(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getSubmissionById(id));
    }

    // 3. CREATE a New Submission
    @PostMapping
    public ResponseEntity<Submission> create(@RequestBody Submission submission) {
        return ResponseEntity.ok(submissionService.createSubmission(submission));
    }

    // 4. UPDADTE an Existing Submission
    @PutMapping("/{id}")
    public ResponseEntity<Submission> update(@PathVariable Long id,
                                        @RequestBody Submission submissionDetails) {
        return ResponseEntity.ok(submissionService.updateSubmission(id, submissionDetails));
    }

    // 5. DELETE Submission
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        submissionService.deleteSubmission(id);
        return ResponseEntity.ok("Submission with ID " + id + " is deleted successfully.");
    }

    // 6. GET Submissions By ConferenceID
    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Submission>> getSubmissionsByConference(@PathVariable Long conferenceId) {
        return ResponseEntity.ok(submissionService.getSubmissionsByConference(conferenceId));
    }

    // 7. GET Submission By Status
    @GetMapping("/status")
    public ResponseEntity<List<Submission>> getSubmissionsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStatus(status));
    }

    // 8. GET Submission By Title Keyword
    @GetMapping("/title")
    public ResponseEntity<List<Submission>> getSubmissionsByTitle(@RequestParam String title) {
        return ResponseEntity.ok(submissionService.searchSubmissionsByTitle(title));
    }
}
