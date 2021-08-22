package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.service.model.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderIntegrationService {

    Optional<List<OrderDto>> listOrders(String customerId);
}
