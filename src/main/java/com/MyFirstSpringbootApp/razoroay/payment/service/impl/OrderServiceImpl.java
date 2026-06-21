package com.MyFirstSpringbootApp.razoroay.payment.service.impl;

import com.MyFirstSpringbootApp.razoroay.common.enums.OrderStatus;
import com.MyFirstSpringbootApp.razoroay.common.exception.BusinessRuleViolationException;
import com.MyFirstSpringbootApp.razoroay.common.exception.DuplicateResourceException;
import com.MyFirstSpringbootApp.razoroay.common.exception.ResourceNotFoundException;
import com.MyFirstSpringbootApp.razoroay.payment.dto.request.OrderCreateRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.PaymentResponse;
import com.MyFirstSpringbootApp.razoroay.payment.entity.OrderRecord;
import com.MyFirstSpringbootApp.razoroay.payment.entity.Payment;
import com.MyFirstSpringbootApp.razoroay.payment.mapper.OrderMapper;
import com.MyFirstSpringbootApp.razoroay.payment.mapper.PaymentMapper;
import com.MyFirstSpringbootApp.razoroay.payment.repository.OrderRepository;
import com.MyFirstSpringbootApp.razoroay.payment.repository.PaymentRepository;
import com.MyFirstSpringbootApp.razoroay.payment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final OrderMapper orderMapper;

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

        return orderMapper.toResponse(orderRecord);
    }

    @Override
    public OrderResponse getById(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("order", orderId));

        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponse cancel(UUID merchantId, UUID orderId) {
        OrderRecord order = orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("order", orderId));

        if(order.getOrderStatus()== OrderStatus.CANCELED ||order.getOrderStatus()== OrderStatus.PAID){
            throw new BusinessRuleViolationException("ORDER_CANNOT_CANCEL",
                    "Cannot cancel order with status: "+order.getOrderStatus().name());
        }


        order.setOrderStatus(OrderStatus.CANCELED);
        order = orderRepository.save(order);
        return orderMapper.toResponse(order);
    }

    @Override
    public List<PaymentResponse> listPayments(UUID merchantId, UUID orderId) {
        orderRepository.findByIdAndMerchantId(orderId, merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("order", orderId));
        List<Payment> paymentList = paymentRepository.findByOrder_Id(orderId);

        return paymentMapper.toResponseList(paymentList);
    }
}
