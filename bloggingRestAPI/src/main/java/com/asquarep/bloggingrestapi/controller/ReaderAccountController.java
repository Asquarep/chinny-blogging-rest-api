package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Reader;
import com.asquarep.bloggingrestapi.service.impl.ReaderServiceImpl;
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
    private final ReaderServiceImpl readerService;

    @GetMapping("/api/reader/login")
    public ResponseEntity<String> readerLogin(@RequestBody LoginDTO loginDTO){
        Long loggingInUserId = readerService.readerLogin(loginDTO);
        if (loggingInUserId == null){
            throw new ResourceNotFoundException("No reader found with login details provided");
        } else{
            return new ResponseEntity<String>("Reader with ID: " + loggingInUserId + " logged in successfully.", HttpStatus.OK);
        }
    }

    @PostMapping("/api/reader/signup")
    public ResponseEntity<String> readerSignUp (@RequestBody SignUpDTO signUpDTO){
        Optional<Reader> createdReader = Optional.ofNullable(readerService.readerSignUp(signUpDTO));
        return createdReader.map(reader -> new ResponseEntity<>("Created new reader with ID: " + reader.getReaderId(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("Sign Up unsuccessful.", HttpStatus.NOT_FOUND));
    }
}
