package com.cms.controller;

import com.cms.model.Registration;
import com.cms.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    // 1. GET All Registration
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        return ResponseEntity.ok(service.getAllRegistrations());
    }

    // 2. GET Registration By RegistrationID
    @GetMapping("/{registrationId}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long registrationId) {
        return ResponseEntity.ok(service.getRegistrationById(registrationId));
    }

    // 3. GET Registration By UserID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Registration>> getRegistrationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getRegistrationsByUser(userId));
    }

    // 4. GET Registration By ConferenceID
    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Registration>> getRegistrationsByConference(@PathVariable Long conferenceId) {
        return ResponseEntity.ok(service.getRegistrationsByConference(conferenceId));
    }

    // 5. GET Registration By Type
    @GetMapping("/type")
    public ResponseEntity<List<Registration>> getRegistrationsByType(@RequestParam String registrationType) {
        return ResponseEntity.ok(service.getRegistrationsByType(registrationType));
    }

    // 6. GET Registration By Attending Status
    @GetMapping("/status")
    public ResponseEntity<List<Registration>> getRegistrationsByAttendingStatus(@RequestParam String attendingStatus) {
        return ResponseEntity.ok(service.getRegistrationsByAttendingStatus(attendingStatus));
    }

    // 7. CREATE Registration
    @PostMapping
    public ResponseEntity<Registration> createRegistration(@RequestBody Registration newRegistration) {
        return ResponseEntity.ok(service.createRegistration(newRegistration));
    }

    // 8. UPDATE Registration
    @PutMapping("/{registrationId}")
    public ResponseEntity<Registration> updateRegistration(@PathVariable Long registrationId, @RequestBody Registration updatedRegistration) {
        return ResponseEntity.ok(service.updateRegistration(registrationId, updatedRegistration));
    }

    // 9. DELETE Registration
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<String> deleteRegistration(@PathVariable Long registrationId) {
        service.deleteRegistration(registrationId);
        return ResponseEntity.ok("Registration with registrationID " + registrationId + " deleted successfully.");
    }
}