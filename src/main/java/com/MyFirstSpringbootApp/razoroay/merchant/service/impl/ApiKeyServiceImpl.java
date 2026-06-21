package com.MyFirstSpringbootApp.razoroay.merchant.service.impl;


import com.MyFirstSpringbootApp.razoroay.common.exception.ResourceNotFoundException;
import com.MyFirstSpringbootApp.razoroay.common.util.RandomizerUtil;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.ApiKeyCreateRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.ApiKey;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.ApiKeyRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.MerchantRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.service.ApiKeyService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {


    private final MerchantRepository merchantRepository;

    private final ApiKeyRepository apiKeyRepository;

    @Override
    @Transactional
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

        apiKey = apiKeyRepository.save(apiKey);


        return new ApiKeyCreateResponse(
                apiKey.getId(),
                keyId,
                rawSecret,
                request.environment()
        );
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {

        return apiKeyRepository.findByMerchantId_Id(merchantId).stream()
                .map(apiKey ->
                        new ApiKeyResponse(
                                apiKey.getId(),
                                apiKey.getKeyId(),
                                apiKey.getEnvironment(),
                                apiKey.getEnabled(),
                                apiKey.getLastUsedAt(),
                                null))
                .toList();
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {

        ApiKey apiKey = apiKeyRepository.findById(keyId)
                        .filter(k-> k.getMerchantId().getId().equals(merchantId))
                        .orElseThrow(()-> new ResourceNotFoundException("ApiKey", keyId));

        apiKey.setEnabled(false);

    }

    @Override
    @Transactional
    public ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k-> k.getMerchantId().getId().equals(merchantId))
                .orElseThrow(()-> new ResourceNotFoundException("ApiKey", keyId));

        String newRawSecret = RandomizerUtil.randomBase64(40);

        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiredAt(LocalDateTime.now().plusHours(24));

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(
                apiKey.getId(),
                apiKey.getKeyId(),
                newRawSecret,
                apiKey.getEnvironment()
        );


    }


}
