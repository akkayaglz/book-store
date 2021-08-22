package com.guliz.bookstore.security.interceptor;

import com.guliz.bookstore.security.model.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestClientInterceptor implements ClientHttpRequestInterceptor {

    private SecurityContext context;

    @Autowired
    public RestClientInterceptor(SecurityContext context) {
        this.context = context;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, context.getToken());
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
