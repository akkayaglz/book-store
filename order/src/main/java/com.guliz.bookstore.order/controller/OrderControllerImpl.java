package com.guliz.bookstore.order.controller;

import com.guliz.bookstore.order.controller.model.OrderListResponse;
import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.OrderService;
import com.guliz.bookstore.order.service.model.OrderDto;
import com.guliz.bookstore.order.service.model.StatisticDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    private static final OrderMapper orderMapper = OrderMapper.INSTANCE;

    private OrderService orderService;

    @Autowired
    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<OrderResponse> newOrder(@RequestBody OrderRequest orderRequest) {

        OrderDto orderDto = orderService.newOrder(orderMapper.toOrderDto(orderRequest));
        OrderResponse orderResponse = orderMapper.toOrderResponse(orderDto);
        orderResponse.setMessage("new order is created..");
        return ResponseEntity.ok(orderResponse);
    }

    @Override
    public ResponseEntity<OrderResponse> getOrderById(String orderId) {
        return ResponseEntity.ok(orderMapper.toOrderResponse(orderService.getOrderById(orderId)));
    }

    @Override
    public ResponseEntity<OrderListResponse> getOrderByCustomerId(String customerId) {
        List<OrderDto> orderDtos = orderService.getOrderByCustomerId(customerId);
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse.setOrderList(orderDtos);
        orderListResponse.setMessage(orderDtos.isEmpty() ? "No order found with given customer Id"
                : "element found with given customer Id.");
        return ResponseEntity.ok(orderListResponse);
    }

    @Override
    public ResponseEntity<OrderListResponse> listByDateRange(@RequestBody OrderRequest orderRequest) {
        List<OrderDto> orderDtos = orderService.listOrderByDateRange(orderRequest.getStartDate(),
                orderRequest.getEndDate());
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse.setOrderList(orderDtos);
        orderListResponse.setMessage(orderDtos.isEmpty() ? "No order found with given date range."
                : orderDtos.size() + " element found with given date range.");
        return ResponseEntity.ok(orderListResponse);
    }

    @Override
    public ResponseEntity<StatisticDto> getMonthlyStatistic(String customerId, int month, int year) {

        return ResponseEntity.ok(orderService.getMontlyStatistic(month, year));
    }
}
