package com.guliz.bookstore.statistic.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MonthlyStatisticDto {

    private int month;
    private int totalOrderCount;
    private int totalBookCount;
    private Double totalPurchasedAmount;

}
