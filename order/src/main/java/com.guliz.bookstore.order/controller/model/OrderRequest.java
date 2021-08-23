package com.guliz.bookstore.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@Validated
public class OrderRequest {

    private String orderId;
    @NotNull(message = "orderId cannot be null")
    private String customerId;
    @NotNull(message = "stockId cannot be null")
    private String stockId;
    @Positive(message = "quantity must be positive")
    private int quantity;
    @DateTimeFormat
    private Date startDate;
    @DateTimeFormat
    private Date endDate;

}
