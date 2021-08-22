package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/customer/v1")
public interface CustomerController {

    @PostMapping(value = "/new-customer")
    ResponseEntity<CustomerResponse> newCustomer();

}
