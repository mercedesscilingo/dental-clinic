package com.example.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    private static final String ERROR = "(400) BAD REQUEST";
    public BadRequestException(String errorDetails) {
        super(ERROR + " " + errorDetails);
    }


}
