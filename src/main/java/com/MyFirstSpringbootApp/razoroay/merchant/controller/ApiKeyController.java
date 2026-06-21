package com.MyFirstSpringbootApp.razoroay.merchant.controller;


import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.ApiKeyCreateRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.service.ApiKeyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping
    public ResponseEntity<List<ApiKeyResponse>> listByMerchant(@PathVariable UUID merchantId){

        return ResponseEntity.ok(apiKeyService.listByMerchant(merchantId));
    }

    @DeleteMapping("/{keyId}")
    public ResponseEntity<String> revoke(@PathVariable UUID merchantId,@PathVariable UUID keyId){

        apiKeyService.revoke(merchantId,keyId);
        return ResponseEntity.ok(Map.of("message", "API key has been revoked").toString());

    }

    @PostMapping("/{keyId}/rotate")
    public ResponseEntity<ApiKeyCreateResponse>  rotate(@PathVariable UUID merchantId, @PathVariable UUID keyId){
        return ResponseEntity.ok(apiKeyService.rotate(merchantId,keyId));
    }

}
