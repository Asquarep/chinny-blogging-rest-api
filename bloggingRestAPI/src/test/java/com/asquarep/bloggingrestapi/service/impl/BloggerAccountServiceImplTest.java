package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.BloggerDTO;
import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class BloggerAccountServiceImplTest {
    @InjectMocks
    private BloggerAccountServiceImpl bloggerService;

    @Mock
    private BloggerRepository bloggerRepository;

    @Mock
    private Converter converter;
    Blogger blogger;
    BloggerDTO bloggerDTO;
    SignUpDTO signUpDTO;
    LoginDTO loginDTO;



    @BeforeEach
    void setUp() {
        blogger = new Blogger();
        blogger.setBlogId(1L);
        blogger.setFirstName("Peter");
        blogger.setLastName("Abiodun");
        blogger.setEmail("peter@gmail.com");
        blogger.setPassword("12345");
        blogger.setRole(Role.valueOf("BLOGGER"));

        signUpDTO = new SignUpDTO();
        signUpDTO.setFirstName("Peter");
        signUpDTO.setLastName("Abiodun");
        signUpDTO.setEmail("peter@gmail.com");
        signUpDTO.setPassword("12345");

        loginDTO = new LoginDTO();
        loginDTO.setEmail("peter@gmail.com");
        loginDTO.setPassword("12345");

        bloggerDTO = new BloggerDTO();
        bloggerDTO.setFirstName("Peter");
        bloggerDTO.setLastName("Abiodun");
        bloggerDTO.setEmail("peter@gmail.com");
        bloggerDTO.setRole(Role.valueOf("BLOGGER"));
    }

    @Test
    public void bloggerLogin() {
        Mockito.when(bloggerRepository.findBloggerByEmail(loginDTO.getEmail())).thenReturn(Optional.ofNullable(blogger));
        ResponseEntity<String> responseEntity = bloggerService.bloggerLogin(loginDTO);
        Assertions.assertThat(Objects.requireNonNull(responseEntity.getBody())).isNotNull();
        Assertions.assertThat(Objects.requireNonNull(responseEntity.getBody()).equals("Blogger with ID: " + blogger.getBlogId() + " logged in successfully."));

        loginDTO.setPassword("234145");
        assertThrows(ResourceNotFoundException.class, () -> bloggerService.bloggerLogin(loginDTO), "No blog found with login details provided");

    }

    @Test
    void bloggerSignUp() {
        Mockito.when(bloggerRepository.findBloggerByEmail(signUpDTO.getEmail())).thenReturn(Optional.empty());
//        Mockito.when(converter.convertBloggerOrDTO(bloggerDTO)).thenReturn(blogger);
        Mockito.when(bloggerRepository.save(any())).thenReturn(blogger);
        ResponseEntity<String> responseEntity = bloggerService.bloggerSignUp(signUpDTO);
        Assertions.assertThat(responseEntity.getBody()).isEqualTo("New Blog created successfully.");
        Mockito.when(bloggerRepository.findBloggerByEmail(signUpDTO.getEmail())).thenReturn(Optional.of(blogger));
        assertThrows(BadRequestException.class, () -> bloggerService.bloggerSignUp(signUpDTO), "Blogger with this email already exists.");
    }

}