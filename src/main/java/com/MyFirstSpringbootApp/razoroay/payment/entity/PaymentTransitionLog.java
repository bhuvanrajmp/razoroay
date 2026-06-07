package com.MyFirstSpringbootApp.razoroay.payment.entity;

import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentEvent;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment payment;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "event")
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "from_status")
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status",nullable = false)
    private PaymentStatus toStatus;

    @Column(length = 100,name = "actor")
    private String actor;

    @Column(name = "occurred_at",length = 100)
    private LocalDateTime occurredAt;




}
