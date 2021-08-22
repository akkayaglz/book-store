package com.guliz.bookstore.security.controller;

import com.guliz.bookstore.security.constants.SecurityConstants;
import com.guliz.bookstore.security.controller.model.SecurityDto;
import com.guliz.bookstore.security.controller.model.SecurityRequest;
import com.guliz.bookstore.security.controller.model.SecurityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping(SecurityConstants.SECURITY_END_POINT)
public interface SecurityController {

    @PostMapping(value = SecurityConstants.REGISTER)
    ResponseEntity<SecurityDto> register(SecurityRequest securityRequest);

    @PostMapping(value = SecurityConstants.LOGIN)
    ResponseEntity<SecurityResponse> login(SecurityRequest securityRequest);

}
