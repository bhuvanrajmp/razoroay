package com.MyFirstSpringbootApp.razoroay.payment.dto.response;

import com.MyFirstSpringbootApp.razoroay.common.entity.Money;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentMethod;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PaymentResponse(

        UUID id,
        UUID merchantId,
        UUID orderId,
        Money amount,
        PaymentStatus status,
        PaymentMethod method,
        Map<String ,Object> methodDetails,
        String errorCode,
        String errorDescription,
        LocalDateTime capturedAt,
        LocalDateTime createdAt
) {
}
