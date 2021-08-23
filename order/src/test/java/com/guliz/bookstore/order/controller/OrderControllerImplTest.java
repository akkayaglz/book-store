package com.guliz.bookstore.order.controller;


import com.guliz.bookstore.order.controller.model.OrderListResponse;
import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.OrderService;
import com.guliz.bookstore.order.service.model.OrderDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

public class OrderControllerImplTest {

    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderControllerImpl orderServiceController;

    private static final OrderMapper mapper = OrderMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private OrderDto orderDto;
    private OrderRequest orderRequest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderRequest = generator.nextObject(OrderRequest.class);
        orderDto = mapper.toOrderDto(orderRequest);
    }

    @Test
    public void testNewOrder() {
        Mockito.when(orderService.newOrder(Mockito.any()))
                .thenReturn(orderDto);
        OrderResponse response = orderServiceController
                .newOrder(orderRequest).getBody();
        Assert.assertEquals("new order is created..",
                Optional.ofNullable(response)
                        .orElseThrow(RuntimeException::new).getMessage());
    }

    @Test
    public void testGetOrderByCustomerId() {
        Mockito.when(orderService.getOrderByCustomerId(Mockito.anyString()))
                .thenReturn(Collections.singletonList(orderDto));
        OrderListResponse response = orderServiceController
                .getOrderByCustomerId("1").getBody();

        Assert.assertEquals("element found with given customer Id.",
                response.getMessage());
    }

    @Test
    public void testGetOrderById() {
        Mockito.when(orderService.getOrderById(
                Mockito.anyString())).thenReturn(orderDto);
        OrderResponse response = orderServiceController
                .getOrderById("orderId").getBody();
        Assert.assertTrue(!response.getOrderId().isEmpty());

    }
}