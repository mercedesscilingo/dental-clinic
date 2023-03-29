package com.example.dentalclinic.service;

import com.example.dentalclinic.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient save(Patient patient);
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
    Optional<Patient> update(Patient patient);
    void delete(Long id);
}
