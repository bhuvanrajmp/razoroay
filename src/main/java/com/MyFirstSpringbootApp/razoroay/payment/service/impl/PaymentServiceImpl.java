package com.MyFirstSpringbootApp.razoroay.payment.service.impl;

import com.MyFirstSpringbootApp.razoroay.common.enums.OrderStatus;
import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentStatus;
import com.MyFirstSpringbootApp.razoroay.common.exception.BusinessRuleViolationException;
import com.MyFirstSpringbootApp.razoroay.common.exception.ResourceNotFoundException;
import com.MyFirstSpringbootApp.razoroay.payment.dto.request.PaymentInitRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.PaymentResponse;
import com.MyFirstSpringbootApp.razoroay.payment.entity.OrderRecord;
import com.MyFirstSpringbootApp.razoroay.payment.entity.Payment;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.PaymentGatewayRouter;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.dto.request.PaymentRequest;
import com.MyFirstSpringbootApp.razoroay.payment.repository.OrderRepository;
import com.MyFirstSpringbootApp.razoroay.payment.repository.PaymentRepository;
import com.MyFirstSpringbootApp.razoroay.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;

    @Override
    @Transactional
    public PaymentResponse initiate(UUID merchantId, PaymentInitRequest request) {

        OrderRecord order = orderRepository.findByIdAndMerchantId(request.orderId(),merchantId).
                orElseThrow(()-> new ResourceNotFoundException("ORDER",request.orderId()));

        if(order.getOrderStatus()!= OrderStatus.CREATED && order.getOrderStatus()!= OrderStatus.ATTEMPTED ){
            throw new BusinessRuleViolationException("ORDER_NOT_PAYABLE",
                    "order cannot accept payment is status :"+order.getOrderStatus());
        }

        order.setOrderStatus(OrderStatus.ATTEMPTED);
        order.setAttempts(order.getAttempts()+1);

        Payment payment = Payment.builder()
                .order(order)
                .merchantId(merchantId)
                .amount(order.getAmount())
                .status(PaymentStatus.CREATED)
                .method(request.method())
                .methodDetails(request.methodDetails())
                .build();

        payment = paymentRepository.save(payment);

        PaymentRequest paymentRequest = new PaymentRequest(
                payment.getId(),
                request.orderId(),
                merchantId,
                order.getAmount(),
                request.method(),
                request.methodDetails()
        );

        paymentGatewayRouter.initiate(paymentRequest);

        return null;
    }
}
