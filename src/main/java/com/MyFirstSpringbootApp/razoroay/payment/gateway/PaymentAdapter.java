package com.MyFirstSpringbootApp.razoroay.payment.gateway;

import com.MyFirstSpringbootApp.razoroay.payment.gateway.dto.request.PaymentRequest;

public interface PaymentAdapter {

    void initiate(PaymentRequest request);
}
