package com.example.dentalclinic.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class AppointmentRegistrationDto {
    private Long id;
    private String date;
    private Long patientId;
    private Long dentistId;
}
