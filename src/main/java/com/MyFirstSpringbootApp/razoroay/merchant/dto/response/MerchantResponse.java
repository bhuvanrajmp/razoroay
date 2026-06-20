package com.MyFirstSpringbootApp.razoroay.merchant.dto.response;


import com.MyFirstSpringbootApp.razoroay.common.enums.BusinessType;
import com.MyFirstSpringbootApp.razoroay.common.enums.MerchantStatus;

import java.util.UUID;

public record MerchantResponse(

        UUID id,
        String name,
        String email,
        String businessName,
        BusinessType businessType,
        MerchantStatus merchantStatus
) {
}
