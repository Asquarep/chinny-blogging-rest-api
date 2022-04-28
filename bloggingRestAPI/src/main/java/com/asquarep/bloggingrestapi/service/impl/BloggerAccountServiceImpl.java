package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.converter.Converter;
import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import com.asquarep.bloggingrestapi.service.BloggerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@AllArgsConstructor
@Service
public class BloggerAccountServiceImpl implements BloggerService {
    private BloggerRepository bloggerRepository;
    private final Converter converter;

    @Override
    public ResponseEntity<String> bloggerLogin(LoginDTO loginDTO) {
        Optional<Blogger> thisBlogger = bloggerRepository.findBloggerByEmail(loginDTO.getEmail());
        if(thisBlogger.isPresent()){
            if(thisBlogger.get().getPassword().equals(loginDTO.getPassword())){
                return new ResponseEntity<String>("Blogger with ID: " + thisBlogger.get().getBlogId() + " logged in successfully.", HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("No blog found with login details provided");
    }

    @Override
    public ResponseEntity<String> bloggerSignUp(SignUpDTO signUpDTO) {
        Optional<Blogger> bloggerCheck = bloggerRepository.findBloggerByEmail(signUpDTO.getEmail());
        if (bloggerCheck.isEmpty()){
            Blogger blogger = new Blogger();
            blogger.setFirstName(signUpDTO.getFirstName());
            blogger.setLastName(signUpDTO.getLastName());
            blogger.setEmail(signUpDTO.getEmail());
            blogger.setPassword(signUpDTO.getPassword());
            blogger.setRole(Role.valueOf("BLOGGER"));
            Blogger blogger1 = bloggerRepository.save(blogger);
            if(blogger1.getBlogId()!=null){
                return new ResponseEntity<String>("New Blog created successfully.", HttpStatus.CREATED);
            }
        }
        throw new BadRequestException("Blogger with this email already exists.");
    }

    @Override
    public Optional<Blogger> getBlogById(Long blogId) {
        return bloggerRepository.findById(blogId);
    }
}
