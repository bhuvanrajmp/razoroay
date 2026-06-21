package com.MyFirstSpringbootApp.razoroay.merchant.repository;

import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.ApiKey;
import com.MyFirstSpringbootApp.razoroay.merchant.service.ApiKeyService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface  ApiKeyRepository extends JpaRepository<ApiKey, UUID> {

    List<ApiKey> findByMerchantId_Id(UUID merchantId);
}
