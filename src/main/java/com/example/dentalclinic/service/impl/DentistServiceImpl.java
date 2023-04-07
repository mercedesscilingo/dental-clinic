package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.repository.DentistRepository;
import com.example.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;


    @Override
    public Dentist save(Dentist dentist) {
        log.debug("Saving dentist");
        return dentistRepository.save(dentist);
    }

    @Override
    public Dentist findById(Long id) {
        return dentistRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist update(Dentist dentist){

        if (dentist.getId() != null && dentistRepository.existsById(dentist.getId()))
            return dentistRepository.save(dentist);
        else
            throw new ResourceNotFoundException("The dentist does not exist");
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting dentist");
        dentistRepository.deleteById(id);
    }

    @Override
    public Dentist getReferenceById(Long id) {
        return dentistRepository.getReferenceById(id);
    }
}
