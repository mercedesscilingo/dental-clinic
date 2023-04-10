package com.example.dentalclinic.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorMessage processNotFoundError(HttpServletRequest request, Exception ex) {

       ErrorMessage errorMessage = ErrorMessage.builder()
               .message(ex.getMessage())
               .httpeStatus(HttpStatus.NOT_FOUND)
               .url(request.getRequestURL().toString())
               .build();

       log.error("Error message: " + errorMessage);

       return errorMessage;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, NumberFormatException.class, NullPointerException.class})
    @ResponseBody
    public ErrorMessage badRequestError(HttpServletRequest request, Exception ex) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .httpeStatus(HttpStatus.BAD_REQUEST)
                .url(request.getRequestURL().toString())
                .build();
        log.error("Error message: " + errorMessage);

        return errorMessage;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public  ErrorMessage internalServerError(HttpServletRequest request, Exception ex){

        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(ex.getMessage())
                .httpeStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .url(request.getRequestURL().toString())
                .build();
        log.error("Error message: " + errorMessage);

        return errorMessage;

    }

}
