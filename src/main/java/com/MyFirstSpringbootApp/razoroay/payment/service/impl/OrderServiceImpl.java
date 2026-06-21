package com.MyFirstSpringbootApp.razoroay.payment.service.impl;

import com.MyFirstSpringbootApp.razoroay.common.enums.OrderStatus;
import com.MyFirstSpringbootApp.razoroay.common.exception.DuplicateResourceException;
import com.MyFirstSpringbootApp.razoroay.payment.dto.request.OrderCreateRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import com.MyFirstSpringbootApp.razoroay.payment.entity.OrderRecord;
import com.MyFirstSpringbootApp.razoroay.payment.repository.OrderRepository;
import com.MyFirstSpringbootApp.razoroay.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Value("${payment.order.default-order-expiry-minutes:30}")
    private int defaultOrderExpiryMinutes;

    @Override
    public OrderResponse create(UUID merchantId,OrderCreateRequest orderCreateRequest) {
        if(orderCreateRequest.receipt() != null &&
                orderRepository.existsByMerchantIdAndReceipt(merchantId,orderCreateRequest.receipt())){
            throw  new DuplicateResourceException("DUPLICATE_RECEIPT_ORDER",
                    "order with receipt already exists: "+orderCreateRequest.receipt() );
        }

        OrderRecord orderRecord = OrderRecord.builder()
                .receipt(orderCreateRequest.receipt())
                .amount(orderCreateRequest.amount())
                .notes(orderCreateRequest.notes())
                .merchantId(merchantId)
                .orderStatus(OrderStatus.CREATED)
                .expiresAt(orderCreateRequest.expiresAt() != null ? orderCreateRequest.expiresAt() :
                        LocalDateTime.now().plusMinutes(defaultOrderExpiryMinutes))
                .build();

        orderRecord =orderRepository.save(orderRecord);

        return new OrderResponse(orderRecord.getId(),
                merchantId,
                orderRecord.getReceipt(),
                orderRecord.getAmount(),
                orderRecord.getOrderStatus(),
                orderRecord.getAttempts(),
                orderRecord.getNotes(),
                orderRecord.getExpiresAt(),
                LocalDateTime.now());
    }
}
