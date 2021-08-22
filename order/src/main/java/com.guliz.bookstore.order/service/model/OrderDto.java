package com.guliz.bookstore.order.service.model;

import lombok.Data;

@Data
public class OrderDto {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
}
