package com.guliz.bookstore.stock.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class BookRequest {

    @NotNull
    private String customerId;
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    private String address;
}
