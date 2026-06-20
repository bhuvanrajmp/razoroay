package com.MyFirstSpringbootApp.razoroay.merchant.dto.response;

import com.MyFirstSpringbootApp.razoroay.common.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(

        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
