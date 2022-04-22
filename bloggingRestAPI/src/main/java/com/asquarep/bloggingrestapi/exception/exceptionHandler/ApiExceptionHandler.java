package com.asquarep.bloggingrestapi.exception.exceptionHandler;

import com.asquarep.bloggingrestapi.exception.BadRequestException;
import com.asquarep.bloggingrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestNotFoundException(ResourceNotFoundException exception){
        // create payload containing exception details
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        TypicalApiException typicalApiException = new TypicalApiException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now());
        return new ResponseEntity<>(typicalApiException, notFound);

    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleApiBadRequestException(BadRequestException exception){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        TypicalApiException typicalApiException = new TypicalApiException(
                exception.getMessage(),
                badRequest,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(typicalApiException, badRequest);
    }
}
