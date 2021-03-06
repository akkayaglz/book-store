package com.guliz.bookstore.customer.controller.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class CustomerResponse extends BaseResponse {

    private static final long serialVersionUID = 3236507658470874840L;

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
