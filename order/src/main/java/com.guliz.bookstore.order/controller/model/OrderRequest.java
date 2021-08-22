package com.guliz.bookstore.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
public class OrderRequest {

    private String orderId;
    @NotNull
    private String customerId;
    @NotNull
    private String stockId;
    private int quantity;
    @DateTimeFormat
    private Date startDate;
    @DateTimeFormat
    private Date endDate;

}
