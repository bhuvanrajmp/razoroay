package com.MyFirstSpringbootApp.razoroay.merchant.dto.request;


import com.MyFirstSpringbootApp.razoroay.common.enums.Environment;

public record ApiKeyCreateRequest(
        Environment environment
) {
}
