package com.MyFirstSpringbootApp.razoroay.payment.repository;

import com.MyFirstSpringbootApp.razoroay.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
