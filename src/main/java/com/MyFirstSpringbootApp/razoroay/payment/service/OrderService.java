package com.MyFirstSpringbootApp.razoroay.payment.service;

import com.MyFirstSpringbootApp.razoroay.payment.dto.request.OrderCreateRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrderService {
    OrderResponse create(UUID merchantId,OrderCreateRequest orderCreateRequest);
}
