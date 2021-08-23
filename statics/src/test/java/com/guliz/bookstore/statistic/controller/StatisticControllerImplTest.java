package com.guliz.bookstore.statistic.controller;

import com.guliz.bookstore.statistic.StatisticService;
import com.guliz.bookstore.statistic.mapper.StatisticMapper;
import com.guliz.bookstore.statistic.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.model.StatisticRequest;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerImplTest {

    @Mock
    private StatisticService statisticService;
    @InjectMocks
    private StatisticControllerImpl controller;

    private static final StatisticMapper mapper = StatisticMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private StatisticRequest statisticRequest;
    private MonthlyStatisticDto monthlyStatisticDto;

    @Before
    public void setup() {
        statisticRequest = generator.nextObject(StatisticRequest.class);
        monthlyStatisticDto = generator.nextObject(MonthlyStatisticDto.class);
    }

    @Test
    public void testGetStatistics() {
        when(statisticService.getStatistics(any())).thenReturn(monthlyStatisticDto);

        ResponseEntity<MonthlyStatisticDto> response = controller.getStatistics(statisticRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
