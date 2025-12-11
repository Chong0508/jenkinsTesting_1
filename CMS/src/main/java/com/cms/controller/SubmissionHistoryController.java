package com.cms.controller;

import com.cms.model.SubmissionHistory;
import com.cms.service.SubmissionHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/submission_history")
@CrossOrigin(origins = "*")
public class SubmissionHistoryController {
    private final SubmissionHistoryService service;
    public SubmissionHistoryController(SubmissionHistoryService service) {
        this.service = service;
    }

    // 1. GET All Submission History
    @GetMapping
    public ResponseEntity<List<SubmissionHistory>> getAllHistory() {
        return ResponseEntity.ok(service.getAllHistory());
    }

    // 2. GET Submission History By ID
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionHistory> getHistoryById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHistoryById(id));
    }

    // 3. CREATE Submission History
    @PostMapping
    public ResponseEntity<SubmissionHistory> createHistory(@RequestBody SubmissionHistory history) {
        return ResponseEntity.ok(service.createHistory(history));
    }

    // 4. DELETE History
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHistory(@PathVariable Long id) {
        service.deleteHistory(id);
        return ResponseEntity.ok("Submission History with historyID " + id + " deleted successfully.");
    }

    // 5. GET Submission History By SubmissionID
    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<SubmissionHistory>> getHistoryBySubmission(@PathVariable Long submissionId) {
        return ResponseEntity.ok(service.getHistoryBySubmission(submissionId));
    }

    // 6. GET Submission History By Old Status
    @GetMapping("/old-status")
    public ResponseEntity<List<SubmissionHistory>> getHistoryByOldStatus(@RequestParam String oldStatus) {
        return ResponseEntity.ok(service.getHistoryByOldStatus(oldStatus));
    }

    // 7. GET Submission History By New Status
    @GetMapping("/new-status")
    public ResponseEntity<List<SubmissionHistory>> getHistoryByNewStatus(@RequestParam String newStatus) {
        return ResponseEntity.ok(service.getHistoryByNewStatus(newStatus));
    }

}