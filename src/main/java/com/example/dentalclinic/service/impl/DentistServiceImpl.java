package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.repository.DentistRepository;
import com.example.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class DentistServiceImpl implements DentistService {

    @Autowired
    private DentistRepository dentistRepository;


    @Override
    public Dentist save(Dentist dentist) {
        if(dentist.getName() == null || dentist.getLastname() == null|| dentist.getLicense() == null){
            throw new BadRequestException("Dentist name, lastname and license must be complete");
        }
        else{
            log.debug("Saving dentist");
            return dentistRepository.save(dentist);
        }
    }

    @Override
    public Dentist findById(Long id) {
        return dentistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + id));
    }

    @Override
    public List<Dentist> findAll() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist update(Dentist dentist){

        if (dentist.getId() != null && dentistRepository.existsById(dentist.getId())){
            log.debug("Updating dentist");
            return dentistRepository.save(dentist);
        }

        else
            throw new ResourceNotFoundException("The dentist does not exist");
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting dentist");
        dentistRepository.deleteById(id);
    }

}
