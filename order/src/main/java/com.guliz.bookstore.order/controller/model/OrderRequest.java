package com.guliz.bookstore.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class OrderRequest {

    private String orderId;
    @NotNull
    private String customerId;
    @NotNull
    private String stockId;
    private int quantity;

}
