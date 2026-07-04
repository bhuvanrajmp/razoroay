package com.MyFirstSpringbootApp.razoroay.payment.gateway;

import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentMethod;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.dto.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentGatewayRouter {

    private final Map<PaymentMethod,PaymentAdapter> paymentAdapterMap;

    public void initiate(PaymentRequest request){

        PaymentAdapter adapter = paymentAdapterMap.get(request.method());
        if(adapter==null){
            throw new IllegalArgumentException("No Payment adapter foud for the requested method :"+request.method());
        }
        adapter.initiate(request);

    }
}
