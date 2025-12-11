package com.cms.repository;

import com.cms.model.SubmissionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionHistoryRepository extends JpaRepository<SubmissionHistory, Long> {

    List<SubmissionHistory> findBySubmissionID_Id(Long submissionId);

    List<SubmissionHistory> findByOldStatus(String oldStatus);

    List<SubmissionHistory> findByNewStatus(String newStatus);
}
