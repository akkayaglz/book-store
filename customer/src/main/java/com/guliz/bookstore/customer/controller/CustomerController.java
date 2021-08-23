package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerRequest;
import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import com.guliz.bookstore.customer.service.model.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RequestMapping("/customer/v1")
public interface CustomerController {

    @PostMapping(value = "/new-customer")
    ResponseEntity<CustomerResponse> newCustomer(CustomerRequest customerRequest);

    @GetMapping(value = "/check")
    ResponseEntity<Boolean> checkCustomer(@NotNull String customerId);

    @GetMapping(value = "/list-orders")
    ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@NotNull String customerId);

}
