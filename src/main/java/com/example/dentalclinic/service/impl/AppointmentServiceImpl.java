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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;


    @Override
    public Appointment save(Appointment appointment) {

        appointment.setPatient(patientRepository.findById(appointment.getPatient().getId())
                .orElseThrow(()->new ResourceNotFoundException("Patient not found")));
        appointment.setDentist(dentistRepository.findById(appointment.getDentist().getId())
                .orElseThrow(()->new ResourceNotFoundException("Dentist not found")));

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

        if (appointment.getId() != null && appointmentRepository.existsById(appointment.getId()))
            return appointmentRepository.save(appointment);
        else
            throw new ResourceNotFoundException("There is no appointment with id " + appointment.getId());
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
}
