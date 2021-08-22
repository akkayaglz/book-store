package com.guliz.bookstore.customer.service.exception;

public class CustomerServiceException extends RuntimeException {
    private static final long serialVersionUID = -3511880960500629881L;

    public CustomerServiceException(String message) {
        super(message);
    }

    public CustomerServiceException(String message, Exception ex) {
        super(message, ex);
    }
}