package com.guliz.bookstore.security.controller.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class SecurityRequest {

    private String username;
    private String password;

}
