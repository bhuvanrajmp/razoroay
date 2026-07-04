package com.MyFirstSpringbootApp.razoroay.merchant.Mapper;

import com.MyFirstSpringbootApp.razoroay.merchant.dto.request.MerchantSignUpRequest;
import com.MyFirstSpringbootApp.razoroay.merchant.dto.response.MerchantResponse;
import com.MyFirstSpringbootApp.razoroay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MerchantMapper {

    Merchant toEntitySignUpRequest(MerchantSignUpRequest merchantSignUpRequest);

    MerchantResponse toResponse(Merchant merchant);

}
