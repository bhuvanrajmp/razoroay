package com.MyFirstSpringbootApp.razoroay.vault.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "card_token")
public class CardToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,length = 50,unique = true)
    private String token;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "vault_card_id")
    private VaultCard vaultCardId;

    @Column(nullable = false,name = "customer_id")
    private UUID customerId;

    @Column(nullable = false,name = "merchant_id")
    private  UUID merchantId;

    private LocalDateTime revokedAt;


}
