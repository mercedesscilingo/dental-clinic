package com.example.dentalclinic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException { //TODO: modificar metodos que lancen exceptions

    private static final String ERROR = "(404) NOT FOUND";
    public ResourceNotFoundException(String errorDetails) {

        super(ERROR + " " + errorDetails);
    }
}
