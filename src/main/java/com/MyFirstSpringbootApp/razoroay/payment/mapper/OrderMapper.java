package com.MyFirstSpringbootApp.razoroay.payment.mapper;

import com.MyFirstSpringbootApp.razoroay.payment.dto.response.OrderResponse;
import com.MyFirstSpringbootApp.razoroay.payment.entity.OrderRecord;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderResponse toResponse(OrderRecord orderRecord);

}
