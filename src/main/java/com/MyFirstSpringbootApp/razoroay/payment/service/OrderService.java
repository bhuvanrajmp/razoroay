package com.MyFirstSpringbootApp.razoroay.payment.service;

import com.MyFirstSpringbootApp.razoroay.payment.dto.request.OrderCreateRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.PaymentResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    OrderResponse create(UUID merchantId,OrderCreateRequest orderCreateRequest);

    OrderResponse getById(UUID merchantId,UUID orderId);

    OrderResponse cancel(UUID merchantId,UUID orderId);

    List<PaymentResponse> listPayments(UUID merchantId,UUID orderId);
}
