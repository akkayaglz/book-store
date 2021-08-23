package com.guliz.bookstore.statistic.controller.service;

import com.guliz.bookstore.statistic.controller.service.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.controller.service.model.StatisticDto;

public interface StatisticService {

    MonthlyStatisticDto getStatistics(StatisticDto statisticDto);

}
