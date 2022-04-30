package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Reader;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.ReaderRepository;
import com.asquarep.bloggingrestapi.service.ReaderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ReaderAccountServiceImplTest {
    @InjectMocks
    private ReaderAccountServiceImpl readerService;

    @Mock
    private ReaderRepository readerRepository;

    @Mock
    private Converter converter;
    Reader reader;
    SignUpDTO signUpDTO;
    LoginDTO loginDTO;



    @BeforeEach
    void setUp() {
        reader = new Reader();
        reader.setReaderId(1L);
        reader.setFirstName("Johnny");
        reader.setLastName("Olugbodi");
        reader.setEmail("johnnysins@gmail.com");
        reader.setPassword("12345");
        reader.setRole(Role.valueOf("READER"));

        signUpDTO = new SignUpDTO();
        signUpDTO.setFirstName("Johnny");
        signUpDTO.setLastName("Olugbodi");
        signUpDTO.setEmail("johnnysins@gmail.com");
        signUpDTO.setPassword("12345");

        loginDTO = new LoginDTO();
        loginDTO.setEmail("johnnysins@gmail.com");
        loginDTO.setPassword("12345");

    }

    @Test
    public void readerLogin() {
        Mockito.when(readerRepository.findReaderByEmail(loginDTO.getEmail())).thenReturn(Optional.ofNullable(reader));
        ResponseEntity<String> responseEntity = readerService.readerLogin(loginDTO);
        Assertions.assertThat(Objects.requireNonNull(responseEntity.getBody())).isNotNull();
        Assertions.assertThat(Objects.requireNonNull(responseEntity.getBody()).equals("Reader with ID: " + reader.getReaderId() + " logged in successfully."));

        loginDTO.setPassword("234145");
        assertThrows(ResourceNotFoundException.class, () -> readerService.readerLogin(loginDTO), "No reader found with login details provided");

    }

    @Test
    void readerSignUp() {
        Mockito.when(readerRepository.findReaderByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(readerRepository.save(any())).thenReturn(reader);
        ResponseEntity<String> responseEntity = readerService.readerSignUp(signUpDTO);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo("New reader account created successfully.");
        Mockito.when(readerRepository.findReaderByEmail(signUpDTO.getEmail())).thenReturn(Optional.of(reader));
        assertThrows(BadRequestException.class, () -> readerService.readerSignUp(signUpDTO), "Reader account with this email already exists.");
    }
}