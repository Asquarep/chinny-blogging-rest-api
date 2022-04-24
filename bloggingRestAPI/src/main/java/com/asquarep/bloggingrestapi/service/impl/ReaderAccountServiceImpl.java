package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.model.Reader;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.ReaderRepository;
import com.asquarep.bloggingrestapi.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReaderAccountServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;


    @Override
    public Long readerLogin(LoginDTO loginDTO) {
        Optional<Reader> thisBlogger = readerRepository.findReaderByEmail(loginDTO.getEmail());
        if(thisBlogger.isPresent()){
            if(thisBlogger.get().getPassword().equals(loginDTO.getPassword())){
                return thisBlogger.get().getReaderId();
            }
        }
        return null;
    }

    @Override
    public Reader readerSignUp(SignUpDTO signUpDTO) {
        Reader reader = new Reader();
        reader.setFirstName(signUpDTO.getFirstName());
        reader.setLastName(signUpDTO.getLastName());
        reader.setEmail(signUpDTO.getEmail());
        reader.setPassword(signUpDTO.getPassword());
        reader.setRole(Role.valueOf("BLOGGER"));
        return readerRepository.save(reader);
    }
}
