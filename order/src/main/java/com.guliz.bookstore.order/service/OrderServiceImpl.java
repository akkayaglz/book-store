package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.data.OrderEntity;
import com.guliz.bookstore.order.data.OrderRepository;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.exception.OrderServiceException;
import com.guliz.bookstore.order.service.model.OrderDto;
import com.guliz.bookstore.stock.service.StockService;
import com.guliz.bookstore.stock.service.model.StockDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final OrderMapper orderMapper = OrderMapper.INSTANCE;

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;
    private CustomerIntegrationService customerIntegrationService;
    private StockService stockService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CustomerIntegrationService customerIntegrationService,
                            StockService stockService) {
        this.orderRepository = orderRepository;
        this.customerIntegrationService = customerIntegrationService;
        this.stockService = stockService;
    }

    @Override
    @Transactional
    public OrderDto newOrder(OrderDto orderDto) {

        if (customerIntegrationService.customerCheck(orderDto.getCustomerId())) {

            synchronized (this) {
                logger.info("checking stock id...");
                StockDto stockDto = stockService.order(orderDto.getStockId(), orderDto.getQuantity());

                logger.info("placing order...");
                OrderEntity orderEntity = placeTheOrder(orderDto, stockDto);

                return orderMapper.toOrderDto(orderEntity);
            }

        }
        throw new OrderServiceException("customer could not found..");
    }

    @Override
    public OrderDto getOrderById(String orderId) {
        return orderMapper.toOrderDto(orderRepository.findById(orderId).get());
    }

    @Override
    public List<OrderDto> listOrderByDateRange(Date start, Date end) {
        List<OrderEntity> orderEntityList = orderRepository.findByCreatedAtBetween(start, end);
        return orderEntityList.stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    private OrderEntity placeTheOrder(OrderDto orderDto, StockDto stockDto) {
        Calendar calendar = Calendar.getInstance();
        orderDto.setCreatedAt(calendar.getTime());
        orderDto.setTotalPrice(stockDto.getPrice() * orderDto.getQuantity());
        return orderRepository.save(orderMapper.toOrderEntity(orderDto));
    }
}
