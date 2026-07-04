package com.MyFirstSpringbootApp.razoroay.operations.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class SettlementPaymentId extends BaseEntity {

    private UUID settlementId;

    private UUID paymentId;

}
