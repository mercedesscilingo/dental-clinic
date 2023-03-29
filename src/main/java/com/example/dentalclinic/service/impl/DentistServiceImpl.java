package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.repository.DentistRepository;
import com.example.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;


    @Override
    public Dentist save(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Optional<Dentist> findById(Long id) {
        return dentistRepository.findById(id);
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Optional<Dentist> update(Dentist dentist) {
        return Optional.of(dentistRepository.save(dentist));
    }

    @Override
    public void delete(Long id) {
        dentistRepository.deleteById(id);
    }
}
