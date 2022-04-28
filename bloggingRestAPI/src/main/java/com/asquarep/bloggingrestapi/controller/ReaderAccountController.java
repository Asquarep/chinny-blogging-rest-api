package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Reader;
import com.asquarep.bloggingrestapi.service.ReaderService;
import com.asquarep.bloggingrestapi.service.impl.ReaderAccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class ReaderAccountController {
    private final ReaderService readerService;

    @GetMapping("/api/reader/login")
    public ResponseEntity<String> readerLogin(@RequestBody LoginDTO loginDTO){
        return readerService.readerLogin(loginDTO);
    }

    @PostMapping("/api/reader/signup")
    public ResponseEntity<String> readerSignUp (@RequestBody SignUpDTO signUpDTO){
        return readerService.readerSignUp(signUpDTO);
    }
}
