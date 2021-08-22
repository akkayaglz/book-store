package com.guliz.bookstore.stock.service.exception.handler;


import com.guliz.bookstore.stock.service.exception.StockServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StockServiceExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StockServiceException.class})
    protected ResponseEntity<Object> handle(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}