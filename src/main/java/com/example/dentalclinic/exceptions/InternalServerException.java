package com.example.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{

    private static final String ERROR = "(500) INTERNAL SERVER ERROR";

    public InternalServerException(String errorDetails){
        super(ERROR + " " + errorDetails);
    }

}
