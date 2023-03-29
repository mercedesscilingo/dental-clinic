package com.example.dentalclinic.controller.dto;


import java.time.LocalDate;

public record AppointmentDto(LocalDate date, Long patientId, Long dentistId) {
}
