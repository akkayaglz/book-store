package com.guliz.bookstore.statistic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticRequest {

    private String customerId;
    private int month;
    private int year;
}
