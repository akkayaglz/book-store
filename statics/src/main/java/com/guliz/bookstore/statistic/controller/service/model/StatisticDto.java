package com.guliz.bookstore.statistic.controller.service.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StatisticDto {

    private String customerId;
    private int month;
    private int year;
}
