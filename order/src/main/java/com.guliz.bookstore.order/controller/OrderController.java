package com.guliz.bookstore.order.controller;

import com.guliz.bookstore.order.controller.model.OrderListResponse;
import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/order/v1")
public interface OrderController {

    @PostMapping(value = "/new-order")
    ResponseEntity<OrderResponse> newOrder(OrderRequest orderRequest);

    @GetMapping(value = "/id")
    ResponseEntity<OrderResponse> getOrderById(String orderId);

    @PostMapping(value = "/list/date-range")
    ResponseEntity<OrderListResponse> listByDateRange(OrderRequest orderRequest);

}
