package com.example.dentalclinic.service;

import com.example.dentalclinic.entity.Dentist;

import java.util.List;
import java.util.Optional;

public interface DentistService {

    Dentist save(Dentist dentist);
    Dentist findById(Long id);
    List<Dentist> findAll();
    Dentist update(Dentist dentist);
    void delete(Long id);

}
