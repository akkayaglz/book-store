package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.exception.OrderServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.eq;

public class CustomerIntegrationServiceImplTest {

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private CustomerIntegrationServiceImpl customerIntegrationServiceImpl;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(customerIntegrationServiceImpl, "host", "localhost");
        ReflectionTestUtils.setField(customerIntegrationServiceImpl, "rootPath", "/customer/v1");
        ReflectionTestUtils.setField(customerIntegrationServiceImpl, "checkPath", "/check");
    }

    @Test
    public void testCustomerCheck() {
        Mockito.when(restTemplate.exchange(
                eq("http://localhost/customer/v1/check?customerId=customerId"),
                eq(HttpMethod.GET),
                eq(null),
                eq(Boolean.class))
        ).thenReturn(ResponseEntity.ok(Boolean.TRUE));
        boolean response = customerIntegrationServiceImpl
                .customerCheck("customerId");
        Assert.assertTrue(response);
    }

    @Test(expected = OrderServiceException.class)
    public void testCheckCustomerExistenceException() {
        Mockito.when(restTemplate.exchange(
                anyString(),
                Mockito.<HttpMethod>any(),
                Mockito.<HttpEntity<?>>any(),
                Mockito.<Class<?>>any()
        )).thenThrow(new RuntimeException());
        customerIntegrationServiceImpl.customerCheck("1");
    }
}
