package com.guliz.bookstore.statistic.service;

import com.guliz.bookstore.statistic.OrderIntegrationService;
import com.guliz.bookstore.statistic.StatisticServiceImpl;
import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.model.StatisticDto;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceImplTest {

    @Mock
    private OrderIntegrationService orderIntegrationService;
    @InjectMocks
    private StatisticServiceImpl statisticService;

    private EasyRandom generator = new EasyRandom();
    private StatisticDto statisticDto;
    private MonthlyStatisticDto monthlyStatisticDto;

    @Before
    public void setup() {
        statisticDto = generator.nextObject(StatisticDto.class);
        monthlyStatisticDto = generator.nextObject(MonthlyStatisticDto.class);
    }

    @Test
    public void testGetStatistics() {
        when(orderIntegrationService.getStatistics(any())).thenReturn(monthlyStatisticDto);

        MonthlyStatisticDto result = statisticService.getStatistics(statisticDto);

        assertEquals(result, monthlyStatisticDto);
    }

}