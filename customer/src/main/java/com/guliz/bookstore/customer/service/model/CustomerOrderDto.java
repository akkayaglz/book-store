package com.guliz.bookstore.customer.service.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerOrderDto {
    private List<OrderDto> orderList;
}
