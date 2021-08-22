package com.guliz.bookstore.customer.service.exception.handler;

import com.guliz.bookstore.customer.controller.model.BaseResponse;
import com.guliz.bookstore.customer.service.exception.CustomerServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerServiceExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomerServiceException.class})
    protected ResponseEntity<BaseResponse> handle(RuntimeException ex) {

        BaseResponse response = new BaseResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}