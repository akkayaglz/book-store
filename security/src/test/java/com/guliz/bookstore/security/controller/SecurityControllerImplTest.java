package com.guliz.bookstore.security.controller;

import com.guliz.bookstore.security.controller.model.SecurityDto;
import com.guliz.bookstore.security.controller.model.SecurityRequest;
import com.guliz.bookstore.security.controller.model.SecurityResponse;
import com.guliz.bookstore.security.exception.SecurityServiceException;
import com.guliz.bookstore.security.service.BookStoreUserDetailService;
import com.guliz.bookstore.security.service.BookStoreUserService;
import com.guliz.bookstore.security.service.JwtGeneratorService;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityControllerImplTest {

    @Mock
    private JwtGeneratorService jwtGeneratorService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private BookStoreUserDetailService detailService;
    @Mock
    private BookStoreUserService userService;

    @InjectMocks
    private SecurityControllerImpl securityController;

    private EasyRandom generator = new EasyRandom();
    private SecurityDto securityDto;
    private SecurityRequest securityRequest;

    @Before
    public void setup() {
        securityDto = generator.nextObject(SecurityDto.class);
        securityRequest = new SecurityRequest();
        securityRequest.setUsername(securityDto.getUsername());
        securityRequest.setPassword(securityDto.getPassword());
    }

    @Test
    public void should_correctly_register() {
        when(userService.register(any())).thenReturn(securityDto);

        SecurityDto response = securityController.register(securityRequest).getBody();

        assertEquals(securityDto.getUsername(), response.getUsername());
        assertEquals(securityDto.getPassword(), response.getPassword());
    }

    @Test
    public void should_correctly_login_and_retrieve_the_token() {
        UserDetails userDetails = mock(UserDetails.class);
        mock(Authentication.class);
        when(detailService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtGeneratorService.generateToken(userDetails)).thenReturn("token");

        SecurityResponse response = securityController.login(securityRequest).getBody();

        assertEquals("token", response.getBarearToken());
    }

    @Test
    public void should_fail() {
        try {
            securityController.login(securityRequest).getBody();
            fail("should fail since incorrect username or password entered");
        } catch (SecurityServiceException e) {
            assertEquals(e.getMessage(), "Incorrect username or password.");
        }
    }

}