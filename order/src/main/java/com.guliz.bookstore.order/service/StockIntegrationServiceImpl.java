package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.exception.OrderServiceException;
import com.guliz.bookstore.order.service.model.OrderStockDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class StockIntegrationServiceImpl implements StockIntegrationService {

    @Value("${stock.service.host:localhost:9094}")
    private String host;

    @Value("${stock.service.root.path:/stock/v1}")
    private String rootPath;

    @Value("${stock.service.order.path:/order-stock}")
    private String orderPath;

    private RestTemplate restTemplate;

    @Autowired
    public StockIntegrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderStockDto orderStock(String stockId, int quantity) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .path(rootPath.concat(orderPath))
                .queryParam("stockId", stockId)
                .queryParam("quantity",quantity).build();

        ResponseEntity<OrderStockDto> orderResponse;
        try {
            orderResponse = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, null, OrderStockDto.class);
        } catch (Exception e) {
            throw new OrderServiceException("Error is occurred in stock service integration process..: {} " + e.getMessage());
        }

        return Optional.ofNullable(orderResponse.getBody())
                .orElseThrow(() -> new OrderServiceException(
                        "Could not get response from customer service."));
    }
}
