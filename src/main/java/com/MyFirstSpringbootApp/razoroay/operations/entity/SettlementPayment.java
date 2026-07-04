package com.MyFirstSpringbootApp.razoroay.operations.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import jakarta.persistence.*;


@Entity
@Table(name = "settlement_payment")
public class SettlementPayment {

    @EmbeddedId
    private SettlementPaymentId id;

    @MapsId("settlementId")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "settlement_id")
    private Settlement settlementId;

}
