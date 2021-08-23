package com.guliz.bookstore.order.controller;

import com.guliz.bookstore.order.controller.model.OrderListResponse;
import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import com.guliz.bookstore.order.service.model.StatisticDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Validated
@RequestMapping("/order/v1")
public interface OrderController {

    @PostMapping(value = "/new-order")
    ResponseEntity<OrderResponse> newOrder(@Valid OrderRequest orderRequest);

    @GetMapping(value = "/id")
    ResponseEntity<OrderResponse> getOrderById(@NotNull String orderId);

    @GetMapping(value = "/customerId")
    ResponseEntity<OrderListResponse> getOrderByCustomerId(@NotNull String customerId);

    @PostMapping(value = "/list/date-range")
    ResponseEntity<OrderListResponse> listByDateRange(@Valid OrderRequest orderRequest);

    @GetMapping(value = "/statistic")
    ResponseEntity<StatisticDto> getMonthlyStatistic(@NotNull String customerId, @Min(2000) @Max(9999) int month, @Min(1) @Max(12) int year);



}
