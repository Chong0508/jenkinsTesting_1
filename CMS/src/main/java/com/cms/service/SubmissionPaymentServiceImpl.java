package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.SubmissionPayment;
import com.cms.repository.SubmissionPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SubmissionPaymentServiceImpl implements SubmissionPaymentService {

    private final SubmissionPaymentRepository submissionPaymentRepository;

    public SubmissionPaymentServiceImpl(SubmissionPaymentRepository submissionPaymentRepository) {
        this.submissionPaymentRepository = submissionPaymentRepository;
    }

    @Override
    public List<SubmissionPayment> getAllPayments() {
        return submissionPaymentRepository.findAll();
    }

    @Override
    public SubmissionPayment getPaymentById(Long id) {
        return submissionPaymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("SubmissionPayment", "id", id));
    }

    @Override
    public SubmissionPayment createPayment(SubmissionPayment payment) {
        return submissionPaymentRepository.save(payment);
    }

    @Override
    public SubmissionPayment updatePayment(Long id, SubmissionPayment paymentDetails) {
        SubmissionPayment existing = getPaymentById(id);

        existing.setSubmissionID(paymentDetails.getSubmissionID());
        existing.setAmount(paymentDetails.getAmount());
        existing.setPaymentDate(paymentDetails.getPaymentDate());
        existing.setPaymentMethod(paymentDetails.getPaymentMethod());
        existing.setStatus(paymentDetails.getStatus());

        return submissionPaymentRepository.save(existing);
    }

    @Override
    public void deletePayment(Long id) {
        SubmissionPayment existing = getPaymentById(id);
        submissionPaymentRepository.delete(existing);
    }

    @Override
    public List<SubmissionPayment> getPaymentsByUser(Long userId) {
        return submissionPaymentRepository.findByUserID_Id(userId);
    }
    @Override
    public List<SubmissionPayment> getPaymentsBySubmission(Long submissionId) {
        return submissionPaymentRepository.findBySubmissionID_Id(submissionId);
    }

    @Override
    public List<SubmissionPayment> getPaymentsByStatus(String status) {
        return submissionPaymentRepository.findByStatus(status);
    }

    @Override
    public List<SubmissionPayment> getPaymentsByMethod(String paymentMethod) {
        return submissionPaymentRepository.findByPaymentMethod(paymentMethod);
    }

    @Override
    public List<SubmissionPayment> getPaymentsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return submissionPaymentRepository.findByPaymentDateBetween(startDate, endDate);
    }
}
