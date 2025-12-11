package com.cms.service;

import com.cms.model.Conference;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceService {

    // Basic CRUD
    Conference createConference(Conference conference);

    List<Conference> getAllConferences();

    Conference getConferenceById(Long id);

    Conference updateConference(Long id, Conference conferenceDetails);

    void deleteConference(Long id);

    // Custom queries (wrapping your repository methods)

    List<Conference> getConferencesByYear(Integer year);

    Conference getConferenceByAcronym(String acronym);

    List<Conference> searchConferencesByName(String keyword);

    List<Conference> getConferencesStartingAfter(LocalDate date);

    List<Conference> getConferencesEndingBefore(LocalDate date);

    List<Conference> getConferencesBetweenDates(LocalDate startDate, LocalDate endDate);

    Conference getConferenceByAcronymAndYear(String acronym, Integer year);
}
