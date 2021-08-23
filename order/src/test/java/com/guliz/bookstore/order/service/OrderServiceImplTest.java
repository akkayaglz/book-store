package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.data.OrderEntity;
import com.guliz.bookstore.order.data.OrderRepository;
import com.guliz.bookstore.order.mapper.OrderMapper;
import com.guliz.bookstore.order.service.exception.OrderServiceException;
import com.guliz.bookstore.order.service.model.OrderDto;
import com.guliz.bookstore.order.service.model.OrderStockDto;
import org.jeasy.random.EasyRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;


public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerIntegrationService customerIntegrationService;
    @Mock
    private StockIntegrationService stockIntegrationService;
    @InjectMocks
    private OrderServiceImpl orderService;

    private static final OrderMapper mapper = OrderMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private OrderDto orderDto;
    private OrderStockDto orderStockDto;
    private OrderEntity orderEntity;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderDto = generator.nextObject(OrderDto.class);
        orderStockDto = generator.nextObject(OrderStockDto.class);
        orderEntity = mapper.toOrderEntity(orderDto);
    }

    @Test
    public void testNewOrder() {
        Mockito.when(customerIntegrationService
                .customerCheck(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(stockIntegrationService.orderStock(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(orderStockDto);
        Mockito.when(orderRepository.save(Mockito.any()))
                .thenReturn(mapper.toOrderEntity(orderDto));
        OrderDto response = orderService.newOrder(orderDto);
        Assert.assertNotNull(response);
    }

    @Test(expected = OrderServiceException.class)
    public void testNewOrderException() {
        Mockito.when(customerIntegrationService
                .customerCheck(Mockito.anyString()))
                .thenReturn(false);
        orderService.newOrder(orderDto);
    }

    @Test
    public void testGetOrderByCustomerId() {
        List<OrderEntity> returnedList = new ArrayList<>();
        returnedList.add(orderEntity);
        Mockito.when(orderRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.of(returnedList));
        List<OrderDto> responseList = orderService.getOrderByCustomerId("customerId");
        assertThat(orderDto, samePropertyValuesAs(responseList.get(0)));
    }

    @Test(expected = OrderServiceException.class)
    public void testGetAllOrdersByCustomerIdException() {
        Mockito.when(orderRepository.findByCustomerId(Mockito.anyString()))
                .thenReturn(Optional.empty());
        orderService.getOrderByCustomerId("customerId");
    }

    @Test
    public void testGetOrderByOrderId() {
        Mockito.when(orderRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(orderEntity));
        OrderDto response = orderService
                .getOrderById("orderId");
        assertThat(orderDto, samePropertyValuesAs(response));
    }

    @Test(expected = OrderServiceException.class)
    public void testGetOrderByOrderIdException() {
        Mockito.when(orderRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.empty());
        orderService.getOrderById("orderId");
    }
}
