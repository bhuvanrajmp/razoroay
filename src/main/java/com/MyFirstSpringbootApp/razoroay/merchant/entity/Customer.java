package com.MyFirstSpringbootApp.razoroay.merchant.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

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
