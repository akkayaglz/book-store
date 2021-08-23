package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerRequest;
import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import com.guliz.bookstore.customer.service.model.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/customer/v1")
public interface CustomerController {

    @PostMapping(value = "/new-customer")
    ResponseEntity<CustomerResponse> newCustomer(CustomerRequest customerRequest);

    @GetMapping(value = "/check")
    ResponseEntity<Boolean> checkCustomer(String customerId);

    @GetMapping(value = "/list-orders")
    ResponseEntity<List<OrderDto>> getOrdersByCustomerId(String customerId);

}
