package com.MyFirstSpringbootApp.razoroay.payment.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.Money;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentMethod;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "order_id",nullable = false)
    private OrderRecord order;


    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(nullable = false,length = 100)
    private String idempotencyKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus Status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod Method;

    @JdbcTypeCode((SqlTypes.JSON))
    @Column(columnDefinition ="jsonb",name = "method_details")
    private Map<String,Object> methodDetails;

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 100)
    private String errorDescription;

    private LocalDateTime capturedAt;

    private LocalDateTime authorizedAt;

    private LocalDateTime failedAt;

    private LocalDateTime refundedAt;


    private LocalDateTime settledAt;



}
