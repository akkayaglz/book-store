package com.guliz.bookstore.customer.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class CustomerRequest {

    @NotNull
    private String customerId;
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    private String address;
}
