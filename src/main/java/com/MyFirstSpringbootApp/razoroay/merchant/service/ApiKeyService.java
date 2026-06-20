package com.MyFirstSpringbootApp.razoroay.merchant.service;



import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.ApiKeyCreateRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ApiKeyService {

     ApiKeyCreateResponse create(UUID merchantId, ApiKeyCreateRequest request);

}
