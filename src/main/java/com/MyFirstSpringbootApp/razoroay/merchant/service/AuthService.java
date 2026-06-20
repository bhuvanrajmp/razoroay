package com.MyFirstSpringbootApp.razoroay.merchant.service;

import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.MerchantSignUpRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.MerchantResponse;


public interface AuthService {
     MerchantResponse signUp( MerchantSignUpRequest request);
}
