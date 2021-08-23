package com.guliz.bookstore.statistic.controller;

import com.guliz.bookstore.statistic.mapper.StatisticMapper;
import com.guliz.bookstore.statistic.model.StatisticRequest;
import com.guliz.bookstore.statistic.StatisticService;
import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticControllerImpl implements StatisticController {

    private static final StatisticMapper statisticMapper = StatisticMapper.INSTANCE;

    private StatisticService statisticService;

    @Autowired
    public StatisticControllerImpl(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public ResponseEntity<MonthlyStatisticDto> getStatistics(@RequestBody StatisticRequest statisticRequest) {

        MonthlyStatisticDto monthlyStatisticDto = statisticService
                .getStatistics(statisticMapper.toStatisticDto(statisticRequest));
        return ResponseEntity.ok(monthlyStatisticDto);
    }
}
