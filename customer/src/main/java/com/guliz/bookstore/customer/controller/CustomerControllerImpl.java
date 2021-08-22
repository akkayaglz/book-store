package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerControllerImpl implements CustomerController{


    @Override
    public ResponseEntity<CustomerResponse> newCustomer() {
        //TODO:
        CustomerResponse customerResponse = new CustomerResponse();
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
