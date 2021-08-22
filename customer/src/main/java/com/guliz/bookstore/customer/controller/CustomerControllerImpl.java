package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerRequest;
import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import com.guliz.bookstore.customer.mapper.CustomerMapper;
import com.guliz.bookstore.customer.service.CustomerService;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerImpl implements CustomerController {

    private static final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private CustomerService customerService;

    @Autowired
    public CustomerControllerImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerResponse> newCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerDto customerDto = customerService.newCustomer(customerMapper.toCustomerDto(customerRequest));
        CustomerResponse customerResponse = customerMapper.toCustomerResponse(customerDto);
        customerResponse.setMessage("new customer is created..");
        return ResponseEntity.ok(customerResponse);
    }

    @Override
    public ResponseEntity<Boolean> checkCustomer(String customerId) {
        return ResponseEntity.ok(customerService.checkCustomer(customerId));
    }
}
