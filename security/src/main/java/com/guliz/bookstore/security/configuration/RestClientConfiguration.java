package com.guliz.bookstore.security.configuration;

import com.guliz.bookstore.security.interceptor.RestClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestClientConfiguration implements WebMvcConfigurer {

    private RestClientInterceptor restClientInterceptor;

    public RestClientConfiguration(RestClientInterceptor restClientInterceptor) {
        this.restClientInterceptor = restClientInterceptor;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();

        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }

        interceptors.add(restClientInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }


}
