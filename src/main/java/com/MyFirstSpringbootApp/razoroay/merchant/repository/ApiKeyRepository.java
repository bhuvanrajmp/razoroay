package com.MyFirstSpringbootApp.razoroay.merchant.repository;

import com.MyFirstSpringbootApp.razoroay.merchant.entity.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  ApiKeyRepository extends JpaRepository<ApiKey, UUID> {

}
