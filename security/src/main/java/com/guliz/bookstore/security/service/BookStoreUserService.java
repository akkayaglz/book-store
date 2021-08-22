package com.guliz.bookstore.security.service;

import com.guliz.bookstore.security.controller.model.SecurityDto;
import com.guliz.bookstore.security.data.CredentialEntity;
import com.guliz.bookstore.security.data.CredentialRepository;
import com.guliz.bookstore.security.exception.SecurityServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookStoreUserService {

    private CredentialRepository credentialRepository;

    @Autowired
    public BookStoreUserService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public User getUserByUsername(String username) {
        CredentialEntity credentialEntity =
                credentialRepository.findById(username)
                        .orElseThrow(() -> new SecurityServiceException("Could not find any user"));
        return new User(credentialEntity.getUsername(),
                credentialEntity.getPassword(),
                new ArrayList<>());
    }

    public SecurityDto register(SecurityDto securityDto) {

        credentialRepository.findById(securityDto.getUsername())
                .ifPresent(user -> {throw new SecurityServiceException("This user is already exist");});
        credentialRepository.save(new CredentialEntity(
                        securityDto.getUsername(), securityDto.getPassword()));
        return securityDto;
    }
}