package com.guliz.bookstore.statistic.controller.service;

import com.guliz.bookstore.statistic.controller.service.model.MonthlyStatisticDto;
import com.guliz.bookstore.statistic.controller.service.model.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class OrderIntegrationServiceImpl implements OrderIntegrationService {

    @Value("${order.service.host:localhost:9092}")
    private String host;

    @Value("${order.service.root.path:/order/v1}")
    private String rootPath;

    @Value("${order.service.statistic.path:/statistic}")
    private String orderPath;

    private RestTemplate restTemplate;

    @Autowired
    public OrderIntegrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MonthlyStatisticDto getStatistics(StatisticDto statisticDto) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .path(rootPath.concat(orderPath))
                .queryParam("customerId", statisticDto.getCustomerId())
                .queryParam("month", statisticDto.getMonth())
                .queryParam("year", statisticDto.getYear())
                .build();

        ResponseEntity<MonthlyStatisticDto> statistics;
        try {
            statistics = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, null, MonthlyStatisticDto.class);
        } catch (Exception e) {
            //TODO:
            throw new RuntimeException("Error is occurred in order service integration process..: {} " + e.getMessage());
        }

        return statistics.getBody();
    }
}
