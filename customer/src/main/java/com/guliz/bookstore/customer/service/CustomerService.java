package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.service.model.CustomerDto;

public interface CustomerService {

    CustomerDto newCustomer(CustomerDto customerDto);
    Boolean checkCustomer(String customerId);

}
