package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.service.exception.CustomerServiceException;
import com.guliz.bookstore.customer.service.model.CustomerOrderDto;
import com.guliz.bookstore.customer.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class OrderIntegrationServiceImpl implements OrderIntegrationService {

    @Value("${order.service.host:localhost:9092}")
    private String host;

    @Value("${order.service.root.path:/order/v1}")
    private String rootPath;

    @Value("${order.service.order.path:/customerId}")
    private String orderPath;

    private RestTemplate restTemplate;

    @Autowired
    public OrderIntegrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<List<OrderDto>> listOrders(String customerId) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .path(rootPath.concat(orderPath))
                .queryParam("customerId", customerId)
                .build();

        ResponseEntity<CustomerOrderDto> orders;
        try {
            orders = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, null, CustomerOrderDto.class);
        } catch (Exception e) {
            throw new CustomerServiceException("Error is occurred in order service integration process..: {} " + e.getMessage());
        }

        return Optional.ofNullable(orders.getBody().getOrderList());
    }
}
