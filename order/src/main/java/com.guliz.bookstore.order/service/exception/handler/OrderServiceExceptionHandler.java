package com.guliz.bookstore.order.service.exception.handler;


import com.guliz.bookstore.order.controller.model.BaseResponse;
import com.guliz.bookstore.order.service.exception.OrderServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class OrderServiceExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {OrderServiceException.class})
    protected ResponseEntity<BaseResponse> handle(RuntimeException ex) {

        BaseResponse response = new BaseResponse();
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}