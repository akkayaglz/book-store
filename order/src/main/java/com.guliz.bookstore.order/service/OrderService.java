package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.model.OrderDto;

import java.util.Date;
import java.util.List;

public interface OrderService {

    OrderDto newOrder(OrderDto orderDto);

    OrderDto getOrderById(String orderId);

    List<OrderDto> listOrderByDateRange(Date start, Date end);

}
