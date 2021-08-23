package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.data.CustomerEntity;
import com.guliz.bookstore.customer.data.CustomerRepository;
import com.guliz.bookstore.customer.mapper.CustomerMapper;
import com.guliz.bookstore.customer.service.exception.CustomerServiceException;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerServiceImpl customerService;

    private EasyRandom generator = new EasyRandom();
    private CustomerDto customerDto;
    private CustomerEntity expectedCustomerEntity;
    private static final CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Before
    public void setup() {
        customerDto = generator.nextObject(CustomerDto.class);
        expectedCustomerEntity = mapper.toCustomerEntity(customerDto);
    }

    @Test
    public void should_correctly_persist_the_customer() {
        when(customerRepository.save(any())).thenReturn(expectedCustomerEntity);

        CustomerDto actualDto = customerService.newCustomer(this.customerDto);

        assertEquals(mapper.toCustomerDto(expectedCustomerEntity), actualDto);
    }

    @Test(expected = CustomerServiceException.class)
    public void testNewCustomerException() {
        when(customerRepository.save(any())).thenThrow(new RuntimeException());
        customerService.newCustomer(this.customerDto);
    }

    @Test
    public void testGetCustomerDetails() {
        when(customerRepository.existsById(anyString())).thenReturn(true);

        Boolean exists = customerService.checkCustomer("customerId");

        assertTrue(exists);
    }

    @Test
    public void should_throw_exception_when_customer_already_exist() {
        when(customerRepository.existsByEmail(anyString())).thenReturn(true);

        try {
            customerService.newCustomer(customerDto);
            fail("should fail ");
        } catch (CustomerServiceException e) {
            assertEquals("Customer is already exists..", e.getMessage());
        }
    }
}