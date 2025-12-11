package com.cms.controller;

import com.cms.model.RegistrationPayment;
import com.cms.service.RegistrationPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;

@RestController
@RequestMapping("/registration_payment")
@CrossOrigin(origins = "*")
public class RegistrationPaymentController {
    private final RegistrationPaymentService service;
    public RegistrationPaymentController(RegistrationPaymentService service) {
        this.service = service;
    }

    // 1. GET All Registration Payment
    @GetMapping
    public ResponseEntity<List<RegistrationPayment>> getAllPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }

    // 2. GET Registration Payment by RegPaymentID
    @GetMapping("/{id}")
    public ResponseEntity<RegistrationPayment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPaymentById(id));
    }

    // 3. CREATE Registration Payment
    @PostMapping
    public ResponseEntity<RegistrationPayment> createRegistrationPayment(@RequestBody RegistrationPayment payment) {
        return ResponseEntity.ok(service.createPayment(payment));
    }

    // 4. UPDATE Registration Payment
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationPayment> updateRegistrationPayment(@PathVariable Long id, @RequestBody RegistrationPayment paymentDetails) {
        return ResponseEntity.ok(service.updatePayment(id, paymentDetails));
    }

    // 5. DELETE Registration Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistrationPayment(@PathVariable Long id) {
        service.deletePayment(id);
        return ResponseEntity.ok("Registration Payment with regPaymentID " + id + " deleted successfully.");
    }

    // 6. GET Registration Payment By RegistrationID
    @GetMapping("/registration/{registrationId}")
    public ResponseEntity<List<RegistrationPayment>> getPaymentsByRegistration(@PathVariable Long registrationId) {
        return ResponseEntity.ok(service.getPaymentsByRegistration(registrationId));
    }

    // 7. GET Registration Payment By Status
    @GetMapping("/status")
    public ResponseEntity<List<RegistrationPayment>> getPaymentsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(service.getPaymentsByStatus(status));
    }

    // 8. GET Registration Payment By Method
    @GetMapping("/method")
    public ResponseEntity<List<RegistrationPayment>> getPaymentsByMethod(@RequestParam String paymentMethod) {
        return ResponseEntity.ok(service.getPaymentsByMethod(paymentMethod));
    }

    // 9. GET Registration Payment Between Dates
    @GetMapping("/between")
    public ResponseEntity<List<RegistrationPayment>> getPaymentsBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(service.getPaymentsBetweenDates(startDate, endDate));
    }
}