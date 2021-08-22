package com.guliz.bookstore.security.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SecurityRequest {

    private String username;
    private String password;

}
