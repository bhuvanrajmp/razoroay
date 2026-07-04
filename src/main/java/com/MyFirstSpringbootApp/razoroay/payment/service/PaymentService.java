package com.MyFirstSpringbootApp.razoroay.payment.service;

import com.MyFirstSpringbootApp.razoroay.payment.dto.request.PaymentInitRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.PaymentResponse;

import java.util.UUID;

public interface PaymentService {

    PaymentResponse initiate(UUID merchantId, PaymentInitRequest request);
}
