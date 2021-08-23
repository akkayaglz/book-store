package com.guliz.bookstore.customer.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
}
