package com.guliz.bookstore.customer.controller;

import com.guliz.bookstore.customer.controller.model.CustomerRequest;
import com.guliz.bookstore.customer.controller.model.CustomerResponse;
import com.guliz.bookstore.customer.mapper.CustomerMapper;
import com.guliz.bookstore.customer.service.CustomerService;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerImplTest {

    @Mock
    private CustomerService service;
    @InjectMocks
    private CustomerControllerImpl controller;

    private static final CustomerMapper mapper = CustomerMapper.INSTANCE;
    private EasyRandom generator = new EasyRandom();
    private CustomerRequest customerRequest;
    private CustomerDto customerDto;

    @Before
    public void setup() {
        customerRequest = generator.nextObject(CustomerRequest.class);
        customerDto = mapper.toCustomerDto(customerRequest);
    }

    @Test
    public void testNewCustomer() {
        when(service.newCustomer(any())).thenReturn(customerDto);

        CustomerResponse response = controller.newCustomer(customerRequest).getBody();

        assertEquals("new customer is created..", response.getMessage());
    }

    @Test
    public void testIsCustomer() {
        when(service.checkCustomer(any())).thenReturn(Boolean.TRUE);

        Boolean exist = controller.checkCustomer("customerId").getBody();

        assertTrue(exist);
    }
}