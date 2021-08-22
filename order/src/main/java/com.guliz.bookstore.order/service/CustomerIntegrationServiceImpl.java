package com.guliz.bookstore.order.service;

import com.guliz.bookstore.order.service.exception.OrderServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
public class CustomerIntegrationServiceImpl implements CustomerIntegrationService {

    @Value("${customer.service.host:localhost:9090}")
    private String host;

    @Value("${customer.service.root.path:/customer/v1}")
    private String rootPath;

    @Value("${customer.service.check.path:/check}")
    private String checkPath;

    private RestTemplate restTemplate;

    @Autowired
    public CustomerIntegrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean customerCheck(String customerId) {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(host)
                .path(rootPath.concat(checkPath))
                .queryParam("customerId", customerId).build();

        ResponseEntity<Boolean> customerResponse;
        try {
            customerResponse = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, null, Boolean.class);
        } catch (Exception e) {
            throw new OrderServiceException("Error is occurred in customer integration process..: {} " + e.getMessage());
        }

        return Optional.ofNullable(customerResponse.getBody())
                .orElseThrow(() -> new OrderServiceException(
                        "Could not get response from customer service."));
    }
}
