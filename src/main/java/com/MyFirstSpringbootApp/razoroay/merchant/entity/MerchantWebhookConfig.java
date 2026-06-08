package com.MyFirstSpringbootApp.razoroay.merchant.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config")
public class MerchantWebhookConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "merchant_id",nullable = false)
    private Merchant merchantId;

    @Column(name = "target_url",nullable = false)
    private String targetUrl;  // www.mesho.com/webhook/success

    @Column(name = "webhook_secret_hash",nullable = false,length = 500)
    private String webhookSecretHash;

    @Column(nullable = false)
    private Boolean enabled =true;

    @Column(length = 255)
    private String eventTypes;  // Comma-separated list of event types to subscribe to

}
