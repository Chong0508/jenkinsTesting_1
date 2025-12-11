package com.cms.controller;

import com.cms.model.Conference;
import com.cms.service.ConferenceService;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferences")
@CrossOrigin(origins = "*") // allow frontend (e.g. React/Vue) to call this API
public class ConferenceController {

    private final ConferenceService conferenceService;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    // 1. READ All Conferences
    @GetMapping
    public ResponseEntity<List<Conference>> getAllConferences() {
        return ResponseEntity.ok(conferenceService.getAllConferences());
    }

    // 2. READ Conference by ID
    @GetMapping("/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Long id) {
        return ResponseEntity.ok(conferenceService.getConferenceById(id));
    }

    // 3. CREATE Conference
    @PostMapping
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        return ResponseEntity.ok(conferenceService.createConference(conference));
    }

    // 4. UPDATE Conference
    @PutMapping("/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id,
                                       @RequestBody Conference conferenceDetails) {
        return ResponseEntity.ok(conferenceService.updateConference(id, conferenceDetails));
    }

    // 5. DELETE Conference
    // http://localhost:8080/conferences/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteConference(@PathVariable Long id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.ok("Conference with ID " + id + " deleted successfully.");
    }

    // 6. GET Conference By Year
    // http://localhost:8080/conferences/year?year=2025
    @GetMapping("/year")
    public ResponseEntity<List<Conference>> getConferenceByYear(@RequestParam Integer year) {
        return ResponseEntity.ok(conferenceService.getConferencesByYear(year));
    }

    // 7. GET Conference By Acronym
    @GetMapping("/acronym")
    public ResponseEntity<Conference> getConferenceByAcronym(@RequestParam String acronym) {
        return ResponseEntity.ok(conferenceService.getConferenceByAcronym(acronym));
    }

    // 8. GET Conferences Starting After
    @GetMapping("/start-after")
    public ResponseEntity<List<Conference>>  getConferencesStartingAfter(@RequestParam LocalDate startDate) {
        return ResponseEntity.ok(conferenceService.getConferencesStartingAfter(startDate));
    }

    // 9. GET Conferences Ending Before
    @GetMapping("/end-before")
    public ResponseEntity<List<Conference>>  getConferencesEndingBefore(@RequestParam LocalDate endDate) {
        return ResponseEntity.ok(conferenceService.getConferencesEndingBefore(endDate));
    }

    // 10. GET Conferences Between Dates
    @GetMapping("/between")
    public ResponseEntity<List<Conference>> getConferencesBetweenDates(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(conferenceService.getConferencesBetweenDates(startDate, endDate));
    }

    // 11. GET Conference By Acronym and Year
    @GetMapping("/acronym-year")
    public ResponseEntity<Conference> getConferenceByAcronymAndYear(@RequestParam String acronym, @RequestParam Integer year) {
        return ResponseEntity.ok(conferenceService.getConferenceByAcronymAndYear(acronym, year));
    }

    // 12. Search Conferences By Keyword
    @GetMapping("/searchConferencesByName")
    public ResponseEntity<List<Conference>> searchConferencesByName(@RequestParam String keyword) {
        return ResponseEntity.ok(conferenceService.searchConferencesByName(keyword));
    }

}
