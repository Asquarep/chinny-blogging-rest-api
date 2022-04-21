package com.asquarep.bloggingrestapi.service;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Reader;

public interface ReaderService {
    public Long readerLogin(LoginDTO loginDTO);
    public Reader readerSignUp(SignUpDTO signUpDTO);


}
