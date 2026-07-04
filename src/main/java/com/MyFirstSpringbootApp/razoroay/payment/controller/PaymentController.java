package com.MyFirstSpringbootApp.razoroay.payment.controller;


import com.MyFirstSpringbootApp.razoroay.payment.dto.request.PaymentInitRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.PaymentResponse;
import com.MyFirstSpringbootApp.razoroay.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    UUID merchantId= UUID.fromString("e3f77317-de53-4416-881b-0b3f53e5d72b"); // TODO: replace with merchantContext

    @PostMapping
    public ResponseEntity<PaymentResponse>  initiate(@Valid @RequestBody PaymentInitRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.initiate(merchantId, request));

    }

}
