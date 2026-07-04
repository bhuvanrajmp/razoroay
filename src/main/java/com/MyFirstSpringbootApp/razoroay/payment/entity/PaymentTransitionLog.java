package com.MyFirstSpringbootApp.razoroay.payment.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentActor;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentEvent;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log",indexes = {
        @Index(name = "idx_payment_transition_log_payment_id",columnList = "payment_id")
})
public class PaymentTransitionLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment paymentID;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "event")
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,name = "from_status")
    private PaymentStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_status",nullable = false)
    private PaymentStatus toStatus;

    @Enumerated(EnumType.STRING)
    @Column(length = 100,name = "actor")
    private PaymentActor actor;

    @Column(name = "occurred_at",length = 100)
    private LocalDateTime occurredAt;




}
