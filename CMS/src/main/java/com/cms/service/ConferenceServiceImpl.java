package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Conference;
import com.cms.repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    // CREATE
    @Override
    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    // READ (all)
    @Override
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    // READ (one) with exception if not found
    @Override
    public Conference getConferenceById(Long id) {
        return conferenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conference", "id", id));
    }

    // UPDATE
    @Override
    public Conference updateConference(Long id, Conference conferenceDetails) {
        Conference existing = conferenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conference", "id", id));

        // adjust setters to match your entity field names
        existing.setName(conferenceDetails.getName());
        existing.setAcronym(conferenceDetails.getAcronym());
        existing.setYear(conferenceDetails.getYear());
        existing.setStartDate(conferenceDetails.getStartDate());
        existing.setEndDate(conferenceDetails.getEndDate());
        existing.setSubmissionDeadline(conferenceDetails.getSubmissionDeadline());
        existing.setNotificationDate(conferenceDetails.getNotificationDate());
        existing.setPcMemberDeadline(conferenceDetails.getPcMemberDeadline());
        existing.setVenueDetails(conferenceDetails.getVenueDetails());

        return conferenceRepository.save(existing);
    }

    // DELETE
    @Override
    public void deleteConference(Long id) {
        Conference existing = conferenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conference", "id", id));
        conferenceRepository.delete(existing);
    }

    // --- Custom query wrappers ---

    @Override
    public List<Conference> getConferencesByYear(Integer year) {
        return conferenceRepository.findByYear(year);
    }

    @Override
    public Conference getConferenceByAcronym(String acronym) {
        return conferenceRepository.findByAcronym(acronym)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Conference", "acronym", acronym));
    }

    @Override
    public List<Conference> searchConferencesByName(String keyword) {
        return conferenceRepository.findByNameContaining(keyword);
    }

    @Override
    public List<Conference> getConferencesStartingAfter(LocalDate date) {
        return conferenceRepository.findByStartDateAfter(date);
    }

    @Override
    public List<Conference> getConferencesEndingBefore(LocalDate date) {
        return conferenceRepository.findByEndDateBefore(date);
    }

    @Override
    public List<Conference> getConferencesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return conferenceRepository.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public Conference getConferenceByAcronymAndYear(String acronym, Integer year) {
        return conferenceRepository.findByAcronymAndYear(acronym, year)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Conference", "acronym + year", acronym + " / " + year));
    }
}
