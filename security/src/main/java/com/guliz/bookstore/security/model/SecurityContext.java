package com.guliz.bookstore.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class SecurityContext {

    private String token;
}