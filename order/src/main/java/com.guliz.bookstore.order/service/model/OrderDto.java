package com.guliz.bookstore.order.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Double totalPrice;
    private Date createdAt;
}
