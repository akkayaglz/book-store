package com.guliz.bookstore.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class BookStoreUserDetailService implements UserDetailsService {

    private BookStoreUserService bookStoreUserService;

    @Autowired
    public BookStoreUserDetailService(
            BookStoreUserService bookStoreUserService) {
        this.bookStoreUserService = bookStoreUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return bookStoreUserService.getUserByUsername(username);
    }
}