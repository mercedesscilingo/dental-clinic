package com.example.dentalclinic.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@Builder
public class ErrorMessage {

    private ZonedDateTime timeStamp;
    private HttpStatus httpeStatus;
    private String message;
    private String url;

    public ErrorMessage(ZonedDateTime timeStamp, HttpStatus httpeStatus, String message, String url) {
        this.timeStamp = ZonedDateTime.now();
        this.httpeStatus = httpeStatus;
        this.message = message;
        this.url = url;
    }
}
