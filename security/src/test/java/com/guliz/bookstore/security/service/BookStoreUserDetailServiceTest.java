package com.guliz.bookstore.security.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreUserDetailServiceTest {

    @Mock
    private BookStoreUserService customerService;

    @InjectMocks
    private BookStoreUserDetailService detailService;

    @Test
    public void testLoadUserByUsername() {
        when(customerService.getUserByUsername(anyString())).thenReturn(buildRetrievedUser());

        UserDetails userDetails = detailService.loadUserByUsername("username");

        assertNotNull(userDetails);
        assertEquals(userDetails.getUsername(), "username");
        assertEquals(userDetails.getPassword(), "password");
    }

    private static User buildRetrievedUser() {
        return (User) User.withUsername("username")
                .password("password")
                .accountExpired(false)
                .accountLocked(false)
                .authorities(new ArrayList<>())
                .build();
    }
}