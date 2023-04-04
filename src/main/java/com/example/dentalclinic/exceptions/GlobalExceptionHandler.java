package com.example.dentalclinic.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {                               //TODO Agregar las exceptions en los metodos que correspondan

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public String processNotFoundError(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public String badRequestError(BadRequestException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    public  String internalServerError(InternalServerException ex){
        return ex.getMessage();
    }


}
