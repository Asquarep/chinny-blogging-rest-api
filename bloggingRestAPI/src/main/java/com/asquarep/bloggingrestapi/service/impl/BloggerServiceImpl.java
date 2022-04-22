package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.BloggerRepository;
import com.asquarep.bloggingrestapi.service.BloggerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class BloggerServiceImpl implements BloggerService {
    private BloggerRepository bloggerRepository;


    @Override
    public List<Blogger> getAllBlogs() {
        return bloggerRepository.findAll();
    }

    @Override
    public Long bloggerLogin(LoginDTO loginDTO) {
        Optional<Blogger> thisBlogger = bloggerRepository.findBloggerByEmail(loginDTO.getEmail());
        if(thisBlogger.isPresent()){
            if(thisBlogger.get().getPassword().equals(loginDTO.getPassword())){
               return thisBlogger.get().getBlogId();
            }
        }
        return null;
    }

    @Override
    public Blogger bloggerSignUp(SignUpDTO signUpDTO) {
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
                return blogger1;
            }
        }
        return null;
    }

    @Override
    public Optional<Blogger> getBlogById(Long blogId) {
        return bloggerRepository.findById(blogId);
    }
}
