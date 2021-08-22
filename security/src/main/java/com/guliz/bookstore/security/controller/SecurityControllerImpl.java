package com.guliz.bookstore.security.controller;

import com.guliz.bookstore.security.controller.model.SecurityDto;
import com.guliz.bookstore.security.controller.model.SecurityRequest;
import com.guliz.bookstore.security.controller.model.SecurityResponse;
import com.guliz.bookstore.security.exception.SecurityServiceException;
import com.guliz.bookstore.security.service.BookStoreUserDetailService;
import com.guliz.bookstore.security.service.BookStoreUserService;
import com.guliz.bookstore.security.service.JwtGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SecurityControllerImpl implements SecurityController {

    private JwtGeneratorService jwtGeneratorService;
    private AuthenticationManager authenticationManager;
    private BookStoreUserDetailService detailService;
    private BookStoreUserService customerService;


    @Autowired
    public SecurityControllerImpl(AuthenticationManager authenticationManager,
                                  BookStoreUserDetailService detailService,
                                  JwtGeneratorService jwtGeneratorService,
                                  BookStoreUserService customerService) {
        this.authenticationManager = authenticationManager;
        this.detailService = detailService;
        this.jwtGeneratorService = jwtGeneratorService;
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<SecurityDto> register(@RequestBody SecurityRequest securityRequest) {
        SecurityDto securityDto = new SecurityDto(
                securityRequest.getUsername(), securityRequest.getPassword());
        return ResponseEntity.ok(customerService.register(securityDto));
    }

    @Override
    public ResponseEntity<SecurityResponse> login(@RequestBody SecurityRequest securityRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            securityRequest.getUsername(),
                            securityRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new SecurityServiceException("Invalid credentials", ex);
        }

        UserDetails userDetails = Optional.ofNullable(detailService.loadUserByUsername(securityRequest.getUsername()))
                .orElseThrow(() -> new SecurityServiceException("Incorrect username or password."));

        return ResponseEntity.ok(buildSecurityResponse(userDetails));
    }

    private SecurityResponse buildSecurityResponse(UserDetails userDetails) {
        SecurityResponse securityResponse = new SecurityResponse();
        securityResponse.setBarearToken(jwtGeneratorService.generateToken(userDetails));
        securityResponse.setMessageText("Please use this with your request.");
        return securityResponse;
    }


}
