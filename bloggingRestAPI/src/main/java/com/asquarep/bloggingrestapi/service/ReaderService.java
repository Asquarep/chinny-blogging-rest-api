package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import org.springframework.http.ResponseEntity;

public interface ReaderService {
    public ResponseEntity<String> readerLogin(LoginDTO loginDTO);
    public ResponseEntity<String> readerSignUp(SignUpDTO signUpDTO);


}
