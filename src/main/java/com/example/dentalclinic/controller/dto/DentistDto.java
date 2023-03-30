package com.example.dentalclinic.controller.dto;

import com.example.dentalclinic.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DentistDto {
    private Long id;
    private String name;
    private String lastname;
    private String license;
    @JsonIgnore
    private Set<Appointment> appointments;
}
