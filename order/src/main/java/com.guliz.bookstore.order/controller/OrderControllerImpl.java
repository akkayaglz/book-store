package com.guliz.bookstore.order.controller;

import com.guliz.bookstore.order.controller.model.OrderListResponse;
import com.guliz.bookstore.order.controller.model.OrderRequest;
import com.guliz.bookstore.order.controller.model.OrderResponse;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.OrderService;
import com.guliz.bookstore.order.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<OrderListResponse> listByDateRange(@RequestBody OrderRequest orderRequest) {
        OrderListResponse orderListResponse = new OrderListResponse();
        orderListResponse
                .setOrderList(orderService.listOrderByDateRange(orderRequest.getStartDate(), orderRequest.getEndDate()));
        return ResponseEntity.ok(orderListResponse);
    }
}
