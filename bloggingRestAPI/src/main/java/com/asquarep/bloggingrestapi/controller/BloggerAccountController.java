package com.asquarep.bloggingrestapi.controller;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.service.BloggerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class BloggerAccountController {
    private final BloggerService bloggerService;

    @GetMapping("/api/blogger/login")
    public ResponseEntity<String> bloggerLogin(@RequestBody LoginDTO loginDTO){
        Long loggingInUserId = bloggerService.bloggerLogin(loginDTO);
        if (loggingInUserId == null){
            throw new ResourceNotFoundException("No blog found with login details provided");
        } else{
            return new ResponseEntity<String>("Blogger with ID: " + loggingInUserId + " logged in successfully.", HttpStatus.OK);
        }
    }

    @PostMapping("/api/blogger/signup")
    public ResponseEntity<String> bloggerSignUp (@RequestBody SignUpDTO signUpDTO){
        Blogger createdBlogger = bloggerService.bloggerSignUp(signUpDTO);
        if(createdBlogger == null){
            throw new BadRequestException("Blogger with this email already exists.");
        }

        return new ResponseEntity<String>("New Blog created successfully.", HttpStatus.CREATED);
//        return createdBlogger.map(blogger -> new ResponseEntity<>("Created new blogger with ID: " + blogger.getBlogId(), HttpStatus.OK)).orElseGet(() -> new BadRequestException("Sign Up was unsuccessful"));
    }

}
