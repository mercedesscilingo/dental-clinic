package com.example.dentalclinic.controller.dto;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private Patient patient;
    private Dentist dentist;
}
