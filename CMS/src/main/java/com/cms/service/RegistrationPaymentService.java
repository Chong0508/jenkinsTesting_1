package com.cms.service;

import com.cms.model.RegistrationPayment;

import java.util.List;
import java.time.LocalDate;

public interface RegistrationPaymentService {

    List<RegistrationPayment> getAllPayments();

    RegistrationPayment getPaymentById(Long id);

    RegistrationPayment createPayment(RegistrationPayment payment);

    RegistrationPayment updatePayment(Long id, RegistrationPayment paymentDetails);

    void deletePayment(Long id);

    List<RegistrationPayment> getPaymentsByRegistration(Long registrationId);

    List<RegistrationPayment> getPaymentsByStatus(String status);

    List<RegistrationPayment> getPaymentsByMethod(String paymentMethod);

    List<RegistrationPayment> getPaymentsBetweenDates(LocalDate startDate, LocalDate endDate);
}
