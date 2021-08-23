package com.guliz.bookstore.statistic.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class StatisticRequest {

    @NotNull
    private String customerId;
    @Range(min = 1, max = 12)
    @NotNull
    private int month;
    @Range(min = 0, max = 9999)
    @NotNull
    private int year;
}
