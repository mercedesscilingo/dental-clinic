package com.example.dentalclinic.controller.dto;
import com.example.dentalclinic.entity.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class PatientDto {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private LocalDate admissionDate;
    private Address address;
}
