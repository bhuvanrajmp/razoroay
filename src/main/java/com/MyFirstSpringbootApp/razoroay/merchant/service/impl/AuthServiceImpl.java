package com.MyFirstSpringbootApp.razoroay.merchant.service.impl;

import com.MyFirstSpringbootApp.razoroay.common.enums.MerchantStatus;
import com.MyFirstSpringbootApp.razoroay.common.enums.UserRole;
import com.MyFirstSpringbootApp.razoroay.common.exception.DuplicateResourceException;
import com.MyFirstSpringbootApp.razoroay.merchant.Mapper.MerchantMapper;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.MerchantSignUpRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.MerchantResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.AppUser;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.AppUserRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.repository.MerchantRepository;
import com.MyFirstSpringbootApp.razoroay.merchant.service.AuthService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor


public class AuthServiceImpl implements AuthService {

    private final MerchantRepository merchantRepository;

    private final AppUserRepository appUserRepository;

    private final MerchantMapper merchantMapper;


    @Override
    @Transactional
    public MerchantResponse signUp(MerchantSignUpRequest request) {

        if (merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL","Merchant with email :"+request.email()+" already exists");
        }

        Merchant merchant = merchantMapper.toEntitySignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);

        merchant = merchantRepository.save(merchant);

        AppUser appUser =AppUser.builder()
                .email(request.name())
                .merchantId(merchant)
                .passwordHash(request.password()) // TODO: encrypt
                .role(UserRole.OWNER)
                .build();

        appUser = appUserRepository.save(appUser);


        return merchantMapper.toResponse(merchant);

    }
}
