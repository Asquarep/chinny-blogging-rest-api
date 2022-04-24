package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.model.Blogger;

import java.util.List;
import java.util.Optional;

public interface BloggerService {
    public Long bloggerLogin(LoginDTO loginDTO);
    public Blogger bloggerSignUp(SignUpDTO signUpDTO);
    public Optional<Blogger> getBlogById(Long blogId);


}
