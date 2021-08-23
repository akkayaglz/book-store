package com.guliz.bookstore.order.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {

    private int month;
    private int totalOrderCount;
    private int totalBookCount;
    private Double totalPurchasedAmount;
}
