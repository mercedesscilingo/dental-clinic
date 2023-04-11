package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.repository.AppointmentRepository;
import com.example.dentalclinic.repository.DentistRepository;
import com.example.dentalclinic.repository.PatientRepository;
import com.example.dentalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DentistRepository dentistRepository;


    @Override
    public Appointment save(Appointment appointment) {

        appointment.setPatient(patientRepository.findById(appointment.getPatient().getId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found")));
        appointment.setDentist(dentistRepository.findById(appointment.getDentist().getId())
                .orElseThrow(()->new ResourceNotFoundException("Dentist not found")));

        log.info("Saving appointment");

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment update(Appointment appointment){

        if (appointment.getId() != null && appointmentRepository.existsById(appointment.getId())){
            log.info("Updating appointment");
            return appointmentRepository.save(appointment);
        }
        else
            log.error("Appointment not exists");
            throw new ResourceNotFoundException("There is no appointment with id " + appointment.getId());
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting appointment");
        appointmentRepository.deleteById(id);
    }
}
