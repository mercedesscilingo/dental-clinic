package com.example.dentalclinic.service;

import com.example.dentalclinic.entity.Dentist;

import java.util.List;
import java.util.Optional;

public interface DentistService {

    Dentist save(Dentist dentist);
    Optional<Dentist> findById(Long id);
    List<Dentist> findAll();
    Optional<Dentist> update(Dentist dentist);
    void delete(Long id);

}
