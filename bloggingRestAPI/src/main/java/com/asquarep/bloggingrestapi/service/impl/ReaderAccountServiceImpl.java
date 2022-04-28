package com.asquarep.bloggingrestapi.service.impl;

import com.asquarep.bloggingrestapi.dto.LoginDTO;
import com.asquarep.bloggingrestapi.dto.SignUpDTO;
import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import com.asquarep.bloggingrestapi.model.Blogger;
import com.asquarep.bloggingrestapi.model.Reader;
import com.asquarep.bloggingrestapi.model.Role;
import com.asquarep.bloggingrestapi.repository.ReaderRepository;
import com.asquarep.bloggingrestapi.service.ReaderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ReaderAccountServiceImpl implements ReaderService {
    private final ReaderRepository readerRepository;


    @Override
    public ResponseEntity<String> readerLogin(LoginDTO loginDTO) {
        Optional<Reader> thisReader = readerRepository.findReaderByEmail(loginDTO.getEmail());
        if(thisReader.isPresent()){
            if(thisReader.get().getPassword().equals(loginDTO.getPassword())){
                return new ResponseEntity<String>("Blogger with ID: " + thisReader.get().getReaderId() + " logged in successfully.", HttpStatus.OK);
            }
        }
        throw new ResourceNotFoundException("No blog found with login details provided");
    }

    @Override
    public ResponseEntity<String> readerSignUp(SignUpDTO signUpDTO) {
        Optional<Reader> readerCheck = readerRepository.findReaderByEmail(signUpDTO.getEmail());
        if (readerCheck.isEmpty()) {
            Reader reader = new Reader();
            reader.setFirstName(signUpDTO.getFirstName());
            reader.setLastName(signUpDTO.getLastName());
            reader.setEmail(signUpDTO.getEmail());
            reader.setPassword(signUpDTO.getPassword());
            reader.setRole(Role.valueOf("BLOGGER"));
            Reader reader1 = readerRepository.save(reader);
            if(reader1.getReaderId() != null){
                return new ResponseEntity<String>("New reader account created successfully.", HttpStatus.CREATED);
            }
        }
        throw new BadRequestException("Reader account with this email already exists.");
    }
}
