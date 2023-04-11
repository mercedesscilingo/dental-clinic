package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Address;
import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.repository.DentistRepository;
import com.example.dentalclinic.repository.PatientRepository;
import com.example.dentalclinic.service.AppointmentService;
import com.example.dentalclinic.service.DentistService;
import com.example.dentalclinic.service.PatientService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    @Test
    @Order(1)
    public void saveAppointmentTest(){
        Address address = new Address(null, "Callao", "1234", "Martin Coronado", "Buenos Aires");
        Patient patient = new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), address, null);
        Dentist dentist = new Dentist(null, "Albus", "Dumbledore", "582369", null);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(2023, 06, 12));
        appointment.setPatient(patientService.save(patient));
        appointment.setDentist(dentistService.save(dentist));

        appointmentService.save(appointment);

        assertNotNull(appointment.getId());
    }

}