package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.model.OrderDto;

public interface OrderService {

    OrderDto newOrder(OrderDto orderDto);

}
