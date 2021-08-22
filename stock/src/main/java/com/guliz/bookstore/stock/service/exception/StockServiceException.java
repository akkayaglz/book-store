package com.guliz.bookstore.stock.service.exception;

public class StockServiceException extends RuntimeException {

    private static final long serialVersionUID = 3337399806345942679L;

    public StockServiceException(String message) {
        super(message);
    }

    public StockServiceException(String message, Exception ex) {
        super(message, ex);
    }
}