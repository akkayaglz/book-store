package com.guliz.bookstore.stock.controller.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse extends BaseResponse {

    private static final long serialVersionUID = 6228041194679713L;

    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

}
