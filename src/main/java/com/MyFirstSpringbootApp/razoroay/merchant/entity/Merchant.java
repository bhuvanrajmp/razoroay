package com.MyFirstSpringbootApp.razoroay.merchant.entity;

import com.MyFirstSpringbootApp.razoroay.common.enums.BusinessType;
import com.MyFirstSpringbootApp.razoroay.common.enums.MerchantStatus;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "merchant")
public class Merchant {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(length = 14)
    private String contactNumber;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(length = 100)
    private String businessName;

    @Column(length = 200)
    private String webSiteUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Store the value as string value in db else it will be 0,1,2
    private MerchantStatus status=MerchantStatus.PENDING_KYC;

    @Column(length = 20)
    private String gstId;

    @Column(length = 20)
    private String panId;

    @Column(length = 200)
    private String settlementBankAccount;

    @Column(length = 20)
    private String settlementBankIfsc;

    @Column(length = 50)
    private String settlementBankAccountHolderName;


}
