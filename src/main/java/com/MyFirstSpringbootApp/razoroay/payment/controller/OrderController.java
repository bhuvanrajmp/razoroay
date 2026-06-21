package com.MyFirstSpringbootApp.razoroay.payment.controller;


import com.MyFirstSpringbootApp.razoroay.payment.dto.request.OrderCreateRequest;
import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import com.MyFirstSpringbootApp.razoroay.payment.service.OrderService;
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
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;

    UUID merchantId= UUID.fromString("e3f77317-de53-4416-881b-0b3f53e5d72b"); // TODO: replace with merchantContext

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderCreateRequest orderCreateRequest ){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(merchantId,orderCreateRequest));

    }
}
