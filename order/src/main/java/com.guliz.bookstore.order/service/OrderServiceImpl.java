package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.data.OrderRepository;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.exception.OrderServiceException;
import com.guliz.bookstore.order.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final OrderMapper orderMapper = OrderMapper.INSTANCE;

    private OrderRepository orderRepository;

    private CustomerIntegrationService customerIntegrationService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerIntegrationService customerIntegrationService) {
        this.orderRepository = orderRepository;
        this.customerIntegrationService = customerIntegrationService;
    }

    @Override
    public OrderDto newOrder(OrderDto orderDto) {

        if (customerIntegrationService.customerCheck(orderDto.getCustomerId())){

            //check stock var mı ?
            try {
                return orderMapper.toOrderDto(orderRepository
                        .save(orderMapper.toOrderEntity(orderDto)));
            } catch (Exception ex) {
                throw new OrderServiceException("Error has been occurred in creating new order process..", ex);
            }

        }
        throw new OrderServiceException("customer could not found..");
    }

}
