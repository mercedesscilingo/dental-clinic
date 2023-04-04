package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
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
            throw new ResourceNotFoundException("There is no dentist with id " + dentist.getId()); //TODO: hay que cambiar esta linea?

    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
            dentistRepository.deleteById(id);
    }
}
