package com.MyFirstSpringbootApp.razoroay.merchant.Mapper;

import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyCreateResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.ApiKeyResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

    ApiKeyCreateResponse toResponse(ApiKey apiKey);

    List<ApiKeyResponse> toResponses(List<ApiKey> apiKeyList);

}
