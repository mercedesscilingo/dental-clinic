package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.repository.PatientRepository;
import com.example.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        patient.setAdmissionDate(LocalDate.now());
        return patientRepository.save(patient);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient update(Patient patient) {

        if (patient.getId() != null && patientRepository.existsById(patient.getId()))
            return patientRepository.save(patient);
        else
            throw new ResourceNotFoundException("There is no patient with id " + patient.getId()); //TODO: hay que cambiar esta linea?
    }

    @Override
    public void delete(Long id) {
        patientRepository.deleteById(id);
    }


}
