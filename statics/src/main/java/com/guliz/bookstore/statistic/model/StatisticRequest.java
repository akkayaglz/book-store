package com.guliz.bookstore.statistic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class StatisticRequest {

    @NotNull
    private String customerId;

    @NotNull
    @Range(min = 1, max = 12)
    private int month;

    @NotNull
    @Range(min = 0, max = 9999)
    private int year;
}
