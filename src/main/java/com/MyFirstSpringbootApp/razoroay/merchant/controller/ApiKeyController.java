package com.MyFirstSpringbootApp.razoroay.merchant.controller;


import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.ApiKeyCreateRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/merchants/{merchantId}/api-keys")
@RequiredArgsConstructor
public class ApiKeyController {

    private final ApiKeyService apiKeyService;

    @PostMapping
    public ResponseEntity<ApiKeyCreateResponse> create(@PathVariable UUID merchantId,
                                                       @Valid @RequestBody ApiKeyCreateRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiKeyService.create(merchantId,request));
    }
}
