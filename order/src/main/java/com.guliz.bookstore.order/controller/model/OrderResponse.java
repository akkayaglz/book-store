package com.guliz.bookstore.order.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse extends BaseResponse {

    private static final long serialVersionUID = -5987563803519434756L;

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Double totalPrice;
    private Date createdAt;

}
