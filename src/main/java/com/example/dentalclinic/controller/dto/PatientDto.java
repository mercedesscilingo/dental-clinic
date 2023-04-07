package com.example.dentalclinic.controller.dto;
import com.example.dentalclinic.entity.Address;
import com.example.dentalclinic.entity.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PatientDto {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private String admissionDate;
    private Address address;
    @JsonIgnore
    private Set<Appointment> appointments;
}
