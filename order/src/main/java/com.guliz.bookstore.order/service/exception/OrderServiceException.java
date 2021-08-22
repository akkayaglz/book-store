package com.guliz.bookstore.order.service.exception;

public class OrderServiceException extends RuntimeException {
    private static final long serialVersionUID = -3511880960500629881L;

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(String message, Exception ex) {
        super(message, ex);
    }
}