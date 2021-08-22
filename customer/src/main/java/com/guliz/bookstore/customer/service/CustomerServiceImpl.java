package com.guliz.bookstore.customer.service;

import com.guliz.bookstore.customer.data.CustomerRepository;
import com.guliz.bookstore.customer.mapper.CustomerMapper;
import com.guliz.bookstore.customer.service.exception.CustomerServiceException;
import com.guliz.bookstore.customer.service.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    private boolean isExistCustomerId(String customerId) {
        return customerRepository.existsById(customerId);
    }

    private boolean isExistEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
