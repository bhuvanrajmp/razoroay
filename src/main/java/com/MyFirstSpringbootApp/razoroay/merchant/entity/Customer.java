package com.MyFirstSpringbootApp.razoroay.merchant.entity;


import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "customer",indexes = {
        @Index(name = "idx_customer_merchant_id",columnList = "merchant_id"),
        @Index(name = "idx_customer_merchant_email",columnList = "email")
})
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="merchant_id",nullable = false)
    private Merchant merchantId;

    @Column(nullable = false,length = 200)
    private String name;

    @Column(nullable = false,length = 200)
    private String email;

    @Column(length = 100)
    private String contactNumber;


    @Column
    private LocalDateTime deletedAt;

}
