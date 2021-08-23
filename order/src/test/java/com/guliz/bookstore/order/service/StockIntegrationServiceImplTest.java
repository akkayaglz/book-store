package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.exception.OrderServiceException;
import com.guliz.bookstore.order.service.model.OrderDto;
import com.guliz.bookstore.order.service.model.OrderStockDto;
import com.guliz.bookstore.security.controller.model.SecurityDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class StockIntegrationServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private StockIntegrationServiceImpl stockIntegrationServiceImpl;

    private static final OrderMapper mapper = OrderMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private OrderStockDto orderStockDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(stockIntegrationServiceImpl, "host", "localhost");
        ReflectionTestUtils.setField(stockIntegrationServiceImpl, "rootPath", "/stock/v1");
        ReflectionTestUtils.setField(stockIntegrationServiceImpl, "orderPath", "/order-stock");
        orderStockDto = generator.nextObject(OrderStockDto.class);
    }

    @Test
    public void testCustomerCheck() {
        Mockito.when(restTemplate.exchange(
                eq("http://localhost/stock/v1/order-stock?stockId=stockId&quantity=2"),
                eq(HttpMethod.GET),
                eq(null),
                eq(OrderStockDto.class))
        ).thenReturn(ResponseEntity.ok(orderStockDto));
        OrderStockDto response = stockIntegrationServiceImpl
                .orderStock("stockId", 2);
        Assert.assertEquals(orderStockDto, response);
    }

    @Test(expected = OrderServiceException.class)
    public void testCheckCustomerExistenceException() {
        Mockito.when(restTemplate.exchange(
                anyString(),
                Mockito.<HttpMethod>any(),
                Mockito.<HttpEntity<?>>any(),
                Mockito.<Class<?>>any()
        )).thenThrow(new RuntimeException());
        stockIntegrationServiceImpl
                .orderStock("stockId", 2);
    }
}
