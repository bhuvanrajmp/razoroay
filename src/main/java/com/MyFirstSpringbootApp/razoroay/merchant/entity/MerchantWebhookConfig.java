package com.MyFirstSpringbootApp.razoroay.merchant.entity;

import com.MyFirstSpringbootApp.razoroay.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "merchant_webhook_config",indexes = {
        @Index(name = "idx_webhook_config_merchant_id",columnList = "merchant_id,enabled")
})
public class MerchantWebhookConfig extends BaseEntity {

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
