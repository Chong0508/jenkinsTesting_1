package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.Registration;
import com.cms.model.Conference;
import com.cms.model.User;
import com.cms.repository.RegistrationRepository;
import com.cms.repository.ConferenceRepository;
import com.cms.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.Instant;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final ConferenceRepository conferenceRepository;
    private final UserRepository userRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository, ConferenceRepository conferenceRepository, UserRepository userRepository) {
        this.registrationRepository = registrationRepository;
        this.conferenceRepository = conferenceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Registration", "id", id));
    }

    @Override
    public Registration createRegistration(Registration newRegistration) {
        // Fetch persisted Conference entity
        Long conferenceId = newRegistration.getConferenceID().getId();
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found with id " + conferenceId));

        newRegistration.setConferenceID(conference);

        // Fetch persisted User entity
        Long userId = newRegistration.getUser().getId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        newRegistration.setUser(user);

        // Set Instant timestamp
        newRegistration.setRegistrationDate(Instant.now());

        return registrationRepository.save(newRegistration);
    }

    @Override
    public Registration updateRegistration(Long id, Registration registrationDetails) {
        Registration existing = getRegistrationById(id);

        existing.setConferenceID(registrationDetails.getConferenceID());
        existing.setRegistrationType(registrationDetails.getRegistrationType());
        existing.setRegistrationDate(registrationDetails.getRegistrationDate());
        existing.setAttendingStatus(registrationDetails.getAttendingStatus());
        existing.setMealPreference(registrationDetails.getMealPreference());
        existing.setSpecialRequests(registrationDetails.getSpecialRequests());

        return registrationRepository.save(existing);
    }

    @Override
    public void deleteRegistration(Long id) {
        Registration existing = getRegistrationById(id);
        registrationRepository.delete(existing);
    }

    @Override
    public List<Registration> getRegistrationsByUser(Long userId) {
        return registrationRepository.findByUser_Id(userId);
    }

    @Override
    public List<Registration> getRegistrationsByConference(Long conferenceId) {
        return registrationRepository.findByConferenceID_Id(conferenceId);
    }

    @Override
    public List<Registration> getRegistrationsByType(String registrationType) {
        return registrationRepository.findByRegistrationType(registrationType);
    }

    @Override
    public List<Registration> getRegistrationsByAttendingStatus(String attendingStatus) {
        return registrationRepository.findByAttendingStatus(attendingStatus);
    }
}
