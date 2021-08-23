package com.guliz.bookstore.statistic;

import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.model.StatisticDto;

public interface OrderIntegrationService {

    MonthlyStatisticDto getStatistics(StatisticDto statisticDto);
}
