package com.MyFirstSpringbootApp.razoroay.payment.config;


import com.MyFirstSpringbootApp.razoroay.common.enums.PaymentMethod;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.PaymentAdapter;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.adapter.CardPaymentAdapter;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.adapter.NetBankingAdapter;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.adapter.UpiPaymentAdapter;
import com.MyFirstSpringbootApp.razoroay.payment.gateway.adapter.WalletPaymentAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;



@Configuration
public class PaymentAdapterConfig {

    @Bean
    public  Map<PaymentMethod, PaymentAdapter> paymentAdapterMap(){
        return Map.of(
                PaymentMethod.CARD , new CardPaymentAdapter(),
                PaymentMethod.NETBANKING , new NetBankingAdapter(),
                PaymentMethod.UPI , new UpiPaymentAdapter(),
                PaymentMethod.WALLET , new WalletPaymentAdapter()

        );
    }
}
