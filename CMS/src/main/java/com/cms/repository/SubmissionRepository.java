package com.cms.repository;

import com.cms.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByConferenceID_Id(Long conferenceId);

    List<Submission> findByStatus(String status);

    List<Submission> findByTitleContaining(String keyword);
}
