package com.cms.repository;

import com.cms.model.SubmissionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubmissionPaymentRepository extends JpaRepository<SubmissionPayment, Long> {

    List<SubmissionPayment> findByUserID_Id(Long userId);
    List<SubmissionPayment> findBySubmissionID_Id(Long submissionId);

    List<SubmissionPayment> findByStatus(String status);

    List<SubmissionPayment> findByPaymentMethod(String paymentMethod);

    List<SubmissionPayment> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
