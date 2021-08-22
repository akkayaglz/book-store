package com.guliz.bookstore.security.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SecurityDto {

    private String username;
    private String password;
}