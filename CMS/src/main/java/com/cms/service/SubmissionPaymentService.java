package com.cms.service;

import com.cms.model.SubmissionPayment;

import java.time.LocalDateTime;
import java.util.List;

public interface SubmissionPaymentService {

    List<SubmissionPayment> getAllPayments();

    SubmissionPayment getPaymentById(Long id);

    SubmissionPayment createPayment(SubmissionPayment payment);

    SubmissionPayment updatePayment(Long id, SubmissionPayment paymentDetails);

    void deletePayment(Long id);

    List<SubmissionPayment> getPaymentsByUser(Long userId);

    List<SubmissionPayment> getPaymentsBySubmission(Long submissionId);

    List<SubmissionPayment> getPaymentsByStatus(String status);

    List<SubmissionPayment> getPaymentsByMethod(String paymentMethod);

    List<SubmissionPayment> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
