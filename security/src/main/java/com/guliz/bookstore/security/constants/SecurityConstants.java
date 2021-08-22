package com.guliz.bookstore.security.constants;

public class SecurityConstants {

    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String SECURITY_END_POINT = "/security/v1";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";

    private SecurityConstants() {
        throw new IllegalStateException("This is a constant class.");
    }
}