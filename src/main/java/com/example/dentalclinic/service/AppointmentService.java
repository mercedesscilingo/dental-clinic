package com.example.dentalclinic.service;

import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;

import java.util.List;


public interface AppointmentService {
    Appointment save(Appointment appointment) throws RuntimeException, BadRequestException;
    Appointment findById(Long id);
    List<Appointment> findAll();
    Appointment update(Appointment appointment) throws ResourceNotFoundException;
    void delete(Long id) throws ResourceNotFoundException;
}
