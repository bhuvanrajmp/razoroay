package com.MyFirstSpringbootApp.razoroay.payment.dto.response;

import com.MyFirstSpringbootApp.razoroay.common.entity.Money;
import com.MyFirstSpringbootApp.razoroay.common.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(

        UUID id,
        UUID merchantId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        Map<String, Object> notes,
        LocalDateTime expiresAt,
        LocalDateTime createdAt

) {
}
