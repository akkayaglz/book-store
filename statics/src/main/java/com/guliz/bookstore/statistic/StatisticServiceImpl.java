package com.guliz.bookstore.statistic;

import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.model.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private OrderIntegrationService orderIntegrationService;

    @Autowired
    public StatisticServiceImpl( OrderIntegrationService orderIntegrationService) {
        this.orderIntegrationService = orderIntegrationService;
    }

    @Override
    public MonthlyStatisticDto getStatistics(StatisticDto statisticDto) {
        return orderIntegrationService.getStatistics(statisticDto);
    }
}
