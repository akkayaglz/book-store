package com.guliz.bookstore.customer.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse extends BaseResponse {

    private static final long serialVersionUID = 3236507658470874840L;

    private String customerId;
    private String customerName;

}
