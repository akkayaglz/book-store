package com.guliz.bookstore.security.service;

import com.guliz.bookstore.security.controller.model.SecurityDto;
import com.guliz.bookstore.security.data.CredentialEntity;
import com.guliz.bookstore.security.data.CredentialRepository;
import com.guliz.bookstore.security.exception.SecurityServiceException;
import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookStoreUserServiceTest {
    @Mock
    private CredentialRepository credentialRepository;

    @InjectMocks
    private BookStoreUserService userService;

    private EasyRandom generator = new EasyRandom();
    private CredentialEntity credentialEntity;
    private SecurityDto securityDto;

    @Before
    public void setup() {
        credentialEntity = generator.nextObject(CredentialEntity.class);
        securityDto = generator.nextObject(SecurityDto.class);
    }

    @Test
    public void should_correctly_retrieve_the_user() {
        when(credentialRepository.findById("username")).thenReturn(Optional.of(credentialEntity));

        User user = userService.getUserByUsername("username");

        assertEquals(credentialEntity.getUsername(), user.getUsername());
        assertEquals(credentialEntity.getPassword(), user.getPassword());
    }

    @Test
    public void should_throw_exception_when_there_is_no_user_found() {
        when(credentialRepository.findById("username")).thenReturn(Optional.empty());
        try {
            User user = userService.getUserByUsername("username");
            fail("should fail since there is no user found");
        } catch (SecurityServiceException e) {
            assertEquals(e.getMessage(), "Could not find any user");
        }
    }

    @Test
    public void should_correctly_save_the_user() {
        when(credentialRepository.save(any())).thenReturn(credentialEntity);

        SecurityDto response = userService.register(securityDto);

        assertEquals(securityDto.getUsername(), response.getUsername());
        assertEquals(securityDto.getPassword(), response.getPassword());
    }

    @Test
    public void should_throw_exception_when_the__user_already_exist() {
        when(credentialRepository.findById(any())).thenReturn(Optional.ofNullable(credentialEntity));

        try {
            SecurityDto response = userService.register(securityDto);
            fail("should fail since the user already exist");
        } catch (SecurityServiceException e) {
            assertEquals(e.getMessage(), "This user is already exist");
        }
    }
}