package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.repository.AppointmentRepository;
import com.example.dentalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;


    @Override
    public Appointment save(Appointment appointment) {

        return appointmentRepository.save(appointment);
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
    public Appointment update(Appointment appointment) throws RuntimeException{

        if (appointment.getId() != null && appointmentRepository.existsById(appointment.getId()))
            return appointmentRepository.save(appointment);
        else
            throw new RuntimeException();

    }

    @Override
    public void delete(Long id) {
            appointmentRepository.deleteById(id);
    }
}
