package com.guliz.bookstore.customer.mapper;

import com.guliz.bookstore.customer.controller.model.CustomerRequest;
import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import com.guliz.bookstore.customer.data.CustomerEntity;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponse toCustomerResponse(CustomerDto customerDto);

    CustomerDto toCustomerDto(CustomerRequest customerRequest);

    CustomerEntity toCustomerEntity(CustomerDto customerDto);

    CustomerDto toCustomerDto(CustomerEntity customerEntity);
}
