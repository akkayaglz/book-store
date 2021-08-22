package com.guliz.bookstore.security.exception;

public class SecurityServiceException extends SecurityException {

    private static final long serialVersionUID = -232760856512061469L;

    public SecurityServiceException(String message, Exception ex) {
        super(message, ex);
    }

    public SecurityServiceException(String message) {
        super(message);
    }
}