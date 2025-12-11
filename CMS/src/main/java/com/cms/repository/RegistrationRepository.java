package com.cms.repository;

import com.cms.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    // All registrations by a user's ID
    List<Registration> findByUser_Id(Long userId);
    List<Registration> findByConferenceID_Id(Long conferenceID);

    List<Registration> findByRegistrationType(String registrationType);

    List<Registration> findByAttendingStatus(String attendingStatus);

}
