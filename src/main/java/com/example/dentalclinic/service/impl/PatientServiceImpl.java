package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.repository.PatientRepository;
import com.example.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Patient update(Patient patient) throws RuntimeException {

        if (patient.getId() != null && patientRepository.existsById(patient.getId()))
            return patientRepository.save(patient);
        else
            throw new RuntimeException();
    }

    @Override
    public void delete(Long id) {patientRepository.deleteById(id);
    }


}
