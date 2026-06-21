package com.MyFirstSpringbootApp.razoroay.payment.dto.request;

import com.MyFirstSpringbootApp.razoroay.common.entity.Money;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record OrderCreateRequest(

        @NotNull(message = "Amount is required")
        Money amount,

        @Size(max = 100)
        String receipt,

        Map<String, Object> notes,

        LocalDateTime expiresAt

) {
}
