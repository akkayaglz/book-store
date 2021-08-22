package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.data.CustomerRepository;
import com.guliz.bookstore.customer.mapper.CustomerMapper;
import com.guliz.bookstore.customer.service.exception.CustomerServiceException;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import com.guliz.bookstore.customer.service.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private CustomerRepository customerRepository;
    private OrderIntegrationService orderIntegrationService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               OrderIntegrationService orderIntegrationService) {
        this.customerRepository = customerRepository;
        this.orderIntegrationService = orderIntegrationService;
    }

    @Override
    public CustomerDto newCustomer(CustomerDto customerDto) {

        if (!isExistEmail(customerDto.getEmail())) {
            try {
                return customerMapper.toCustomerDto(customerRepository
                        .save(customerMapper.toCustomerEntity(customerDto)));
            } catch (Exception ex) {
                throw new CustomerServiceException("Error has been occurred in creating new customer process..", ex);
            }
        }
        throw new CustomerServiceException("Customer is already exists..");
    }

    @Override
    public Boolean checkCustomer(String customerId) {
        return isExistCustomerId(customerId);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(String customerId) {
        Optional<List<OrderDto>> orderDtos = orderIntegrationService.listOrders(customerId);
        if(orderDtos.get().isEmpty()){
            throw new CustomerServiceException("no orders have been found for given customer id");
        }
        return orderDtos.get();
    }

    private boolean isExistCustomerId(String customerId) {
        return customerRepository.existsById(customerId);
    }

    private boolean isExistEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
