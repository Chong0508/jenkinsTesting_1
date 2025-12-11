package com.cms.service;

import com.cms.exception.ResourceNotFoundException;
import com.cms.model.RegistrationPayment;
import com.cms.repository.RegistrationPaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RegistrationPaymentServiceImpl implements RegistrationPaymentService {

    private final RegistrationPaymentRepository registrationPaymentRepository;

    public RegistrationPaymentServiceImpl(RegistrationPaymentRepository registrationPaymentRepository) {
        this.registrationPaymentRepository = registrationPaymentRepository;
    }

    @Override
    public List<RegistrationPayment> getAllPayments() {
        return registrationPaymentRepository.findAll();
    }

    @Override
    public RegistrationPayment getPaymentById(Long id) {
        return registrationPaymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RegistrationPayment", "id", id));
    }

    @Override
    public RegistrationPayment createPayment(RegistrationPayment payment) {
        return registrationPaymentRepository.save(payment);
    }

    @Override
    public RegistrationPayment updatePayment(Long id, RegistrationPayment paymentDetails) {
        RegistrationPayment existing = getPaymentById(id);

        existing.setRegistrationID(paymentDetails.getRegistrationID());
        existing.setAmount(paymentDetails.getAmount());
        existing.setPaymentDate(paymentDetails.getPaymentDate());
        existing.setPaymentMethod(paymentDetails.getPaymentMethod());
        existing.setStatus(paymentDetails.getStatus());

        return registrationPaymentRepository.save(existing);
    }

    @Override
    public void deletePayment(Long id) {
        RegistrationPayment existing = getPaymentById(id);
        registrationPaymentRepository.delete(existing);
    }

    @Override
    public List<RegistrationPayment> getPaymentsByRegistration(Long registrationId) {
        return registrationPaymentRepository.findByRegistrationID_Id(registrationId);
    }

    @Override
    public List<RegistrationPayment> getPaymentsByStatus(String status) {
        return registrationPaymentRepository.findByStatus(status);
    }

    @Override
    public List<RegistrationPayment> getPaymentsByMethod(String paymentMethod) {
        return registrationPaymentRepository.findByPaymentMethod(paymentMethod);
    }

    @Override
    public List<RegistrationPayment> getPaymentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return registrationPaymentRepository.findByPaymentDateBetween(startDate, endDate);
    }
}
