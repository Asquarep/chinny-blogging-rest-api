package com.asquarep.bloggingrestapi.exception.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
@Getter
public class TypicalApiException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public TypicalApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}
