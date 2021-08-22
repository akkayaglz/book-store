package com.guliz.bookstore.customer.service.model;

import com.guliz.bookstore.customer.controller.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDto {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Double totalPrice;
    private Date createdAt;

}
