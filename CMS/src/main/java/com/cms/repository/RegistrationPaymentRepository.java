package com.cms.repository;

import com.cms.model.RegistrationPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistrationPaymentRepository extends JpaRepository<RegistrationPayment, Long> {

    List<RegistrationPayment> findByRegistrationID_Id(Long registrationId);

    List<RegistrationPayment> findByStatus(String status);

    List<RegistrationPayment> findByPaymentMethod(String paymentMethod);

    List<RegistrationPayment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
}
