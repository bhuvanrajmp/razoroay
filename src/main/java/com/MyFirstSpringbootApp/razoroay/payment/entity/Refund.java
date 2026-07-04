package com.MyFirstSpringbootApp.razoroay.payment.entity;


import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import com.MyFirstSpringbootApp.razoroay.common.entity.Money;
import com.MyFirstSpringbootApp.razoroay.common.enums.RefundStatus;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "refund")
public class Refund extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "payment_id",nullable = false)
    private Payment paymentId;

    @Column(name = "merchant_id",nullable = false)
    private UUID merchantId;
    // This comes from different domain i.e merchant so no join or direct relation or no FK - cross-service boundary

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundStatus status=RefundStatus.PENDING;

    @Column(length = 100)
    private String bankReference;

    @Column(length = 100)
    private String errorCode;

    @Column(length = 500)
    private String errorDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> notes;

    private LocalDateTime processedAt;



}
