package com.MyFirstSpringbootApp.razoroay.operations.entity;

import com.MyFirstSpringbootApp.razoroay.common.enums.WebHookEventStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.security.KeyRep;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "Webhook_event")
public class WebhookEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false , length = 40)
    private  String eventTpe;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private WebHookEventStatus status;

    @Column(nullable = false)
    private Integer attempts=0;


    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttempt;

    private Integer lastResponseCode;

    @Column(length = 1000)
    private String lastResponseBody;

    private LocalDateTime deliveredAt;


}
