package com.example.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends RuntimeException {

    private static final String ERROR = "(404) NOT FOUND";
    public ResourceNotFoundException(String errorDetails) {

        super(ERROR + " " + errorDetails);
    }
}
