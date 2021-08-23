package com.guliz.bookstore.statistic.controller;

import com.guliz.bookstore.statistic.model.StatisticRequest;
import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/statistics/v1")
public interface StatisticController {

    @PostMapping(value = "/monthly-order")
    ResponseEntity<MonthlyStatisticDto> getStatistics(StatisticRequest statisticRequest);

}
