package com.cms.service;

import com.cms.model.Registration;

import java.util.List;

public interface RegistrationService {

    List<Registration> getAllRegistrations();

    Registration getRegistrationById(Long id);

    Registration createRegistration(Registration registration);

    Registration updateRegistration(Long id, Registration registrationDetails);

    void deleteRegistration(Long id);

    List<Registration> getRegistrationsByUser(Long userId);

    List<Registration> getRegistrationsByConference(Long conferenceId);

    List<Registration> getRegistrationsByType(String registrationType);

    List<Registration> getRegistrationsByAttendingStatus(String attendingStatus);
}
