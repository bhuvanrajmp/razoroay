package com.MyFirstSpringbootApp.razoroay.payment.dto.request;

import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record PaymentInitRequest(

        @NotNull(message = "order id is required")
        UUID orderId,

        @NotNull(message = "payment method id is required")
        PaymentMethod method,


        Map<String,Object> methodDetails
) {
}
