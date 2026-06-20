package com.MyFirstSpringbootApp.razoroay.merchant.service.impl;


import com.MyFirstSpringbootApp.razoroay.common.exception.ResourceNotFoundException;
import com.MyFirstSpringbootApp.razoroay.common.util.RandomizerUtil;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.ApiKeyCreateRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.ApiKey;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.ApiKeyRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.MerchantRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiKeyServiceImpl implements ApiKeyService {


    private final MerchantRepository merchantRepository;

    private final ApiKeyRepository apiKeyRepository;

    @Override
    public ApiKeyCreateResponse create(UUID merchantId, ApiKeyCreateRequest request) {

        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(()-> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        ApiKey apiKey = ApiKey.builder()
                .merchantId(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret)
                .environment(request.environment())
                .build();

        apiKeyRepository.save(apiKey);


        return new ApiKeyCreateResponse(
                apiKey.getId(),
                keyId,
                rawSecret,
                request.environment()
        );
    }
}
