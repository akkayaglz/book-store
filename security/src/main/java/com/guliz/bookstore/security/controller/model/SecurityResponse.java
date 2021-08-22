package com.guliz.bookstore.security.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SecurityResponse {

    String barearToken;
    String messageText;

}
