package com.example.dentalclinic.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity(name = "dentists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String license;
    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)
    private Set<Appointment> appointments;


}
