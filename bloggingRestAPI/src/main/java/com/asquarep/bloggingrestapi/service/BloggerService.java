package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface BloggerService {
    public ResponseEntity<String> bloggerLogin(LoginDTO loginDTO);
    public ResponseEntity<String> bloggerSignUp(SignUpDTO signUpDTO);
    public Optional<Blogger> getBlogById(Long blogId);


}
