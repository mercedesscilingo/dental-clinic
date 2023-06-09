package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.repository.PatientRepository;
import com.example.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        if (patient.getName() == null || patient.getLastname() == null || patient.getDocument() == null ) {
            log.error("Patient name, lastname or document invalid");
            throw new BadRequestException("Error, patient name, lastname and document must be complete");
        }
        else {
            log.info("Saving patient");
            patient.setAdmissionDate(LocalDate.now());
            return patientRepository.save(patient);
        }
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient update(Patient patient) {

        if (patient.getId() != null && patientRepository.existsById(patient.getId())){
            log.info("Updating patient");
            return patientRepository.save(patient);
        }
        else
            throw new ResourceNotFoundException("There is no patient with id " + patient.getId());
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting patient");
        patientRepository.deleteById(id);
    }

}
