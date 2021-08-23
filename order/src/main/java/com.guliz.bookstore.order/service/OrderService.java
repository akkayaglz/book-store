package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.data.OrderEntity;
import com.guliz.bookstore.order.service.model.OrderDto;
import com.guliz.bookstore.order.service.model.StatisticDto;

import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderDto newOrder(OrderDto orderDto);

    OrderDto getOrderById(String orderId);

    List<OrderDto> getOrderByCustomerId(String customerId);

    List<OrderDto> listOrderByDateRange(Date start, Date end);

    StatisticDto getMontlyStatistic(int month, int year);

}
