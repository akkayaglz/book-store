package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.service.model.CustomerDto;
import com.guliz.bookstore.customer.service.model.OrderDto;

import java.util.List;

public interface CustomerService {

    CustomerDto newCustomer(CustomerDto customerDto);

    Boolean checkCustomer(String customerId);

    List<OrderDto> getOrdersByCustomerId(String customerId);

}
