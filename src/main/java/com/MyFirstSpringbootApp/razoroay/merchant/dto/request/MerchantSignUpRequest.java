package com.MyFirstSpringbootApp.razoroay.merchant.dto.request;

import com.MyFirstSpringbootApp.razoroay.common.enums.BusinessType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MerchantSignUpRequest(

        @NotNull(message = "name  is required")
        @Size(max = 50,message = "name should not be more then 50 characters long")
        String name,

        @Email
        @NotNull(message = "Email is required")
        String email,

        @NotNull(message = "password  is required")
        @Size(min = 8,message = "password should be min 8 characters long")
        String password,

        @Size(max = 50,message = "businessName should not be more then 50 characters long")
        String businessName,


        BusinessType businessType
) {
}
