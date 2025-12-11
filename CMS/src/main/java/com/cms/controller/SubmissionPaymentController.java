package com.cms.controller;

import com.cms.model.SubmissionPayment;
import com.cms.service.SubmissionPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/submission_payment")
@CrossOrigin(origins = "*")
public class SubmissionPaymentController {
    private final SubmissionPaymentService service;
    public SubmissionPaymentController(SubmissionPaymentService service) {
        this.service = service;
    }

    // 1. GET All Submission Payments
    @GetMapping
    public ResponseEntity<List<SubmissionPayment>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    // 2. GET  Submission Payment By ID
    @GetMapping("/{id}")
    public ResponseEntity<SubmissionPayment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentById(id));
    }

    // 3. CREATE Submission Payment
    @PostMapping
    public ResponseEntity<SubmissionPayment> createPayment(@RequestBody SubmissionPayment payment) {
        return ResponseEntity.ok(service.createPayment(payment));
    }

    // 4. UPDATE Submission Payment
    @PutMapping("/{id}")
    public ResponseEntity<SubmissionPayment> updatePayment(@PathVariable Long id, @RequestBody SubmissionPayment paymentDetails) {
        return ResponseEntity.ok(service.updatePayment(id, paymentDetails));
    }

    // 5. DELETE Submission Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return ResponseEntity.ok("Submission Payment with subPaymentID " + id + " deleted successfully.");
    }

    // 6. GET Submission Payments By UserID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SubmissionPayment>> getPaymentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getPaymentsByUser(userId));
    }

    // 7. GET Submission Payments By SubmissionID
    @GetMapping("/submission/{submissionId}")
    public ResponseEntity<List<SubmissionPayment>> getPaymentsBySubmission(@PathVariable Long submissionId) {
        return ResponseEntity.ok(service.getPaymentsBySubmission(submissionId));
    }

    // 8. GET Submission Payments By Status
    @GetMapping("/status")
    public ResponseEntity<List<SubmissionPayment>> getPaymentsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(service.getPaymentsByStatus(status));
    }

    // 9. GET Submission Payments By Method
    @GetMapping("/method")
    public ResponseEntity<List<SubmissionPayment>> getPaymentsByMethod(@RequestParam String paymentMethod) {
        return ResponseEntity.ok(service.getPaymentsByMethod(paymentMethod));
    }

    // 10. GET Submission Payments Between Dates
    @GetMapping("/between")
    public ResponseEntity<List<SubmissionPayment>> getPaymentsBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return ResponseEntity.ok(service.getPaymentsBetweenDates(startDate, endDate));
    }

}