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

        if(patientRepository.findById(appointment.getPatient().getId()).isPresent() &&
                dentistRepository.findById(appointment.getDentist().getId()).isPresent()){

            appointment.setPatient(patientRepository.getReferenceById(appointment.getPatient().getId()));
            appointment.setDentist(dentistRepository.getReferenceById(appointment.getDentist().getId()));

            return appointmentRepository.save(appointment);
        }
        else
            throw new BadRequestException("Patient or dentist do not exist");

    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
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
