package com.example.dentalclinic.service;

import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Patient save(Patient patient);
    Patient findById(Long id);
    List<Patient> findAll();
    Patient update(Patient patient);
    void delete(Long id);
}
