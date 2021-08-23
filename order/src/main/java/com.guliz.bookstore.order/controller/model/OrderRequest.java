package com.guliz.bookstore.order.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String orderId;
    private String customerId;
    private String stockId;
    private int quantity;
    private Date startDate;
    private Date endDate;

}
