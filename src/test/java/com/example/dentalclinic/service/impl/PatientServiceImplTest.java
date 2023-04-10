package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Address;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.service.PatientService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    @Test
    @Order(1)
    public void savePatientTest(){
        Address newAddress = new Address(null, "Callao", "1234", "Martin Coronado", "Buenos Aires");
        Patient newPatient = patientService.save( new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), newAddress, null));

        assertEquals(1L, newPatient.getId());
    }

    @Test
    @Order(2)
    public void findByIdPatientTest(){
        assertNotNull(patientService.findById(1L).getId());
    }

    @Test
    @Order(3)
    public void findAllTest(){
        Address newAddress = new Address(null, "Callao", "1234", "Martin Coronado", "Buenos Aires");
        Patient newPatient = patientService.save( new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), newAddress, null));

        List<Patient> patientList = patientService.findAll();

        assertFalse(patientList.isEmpty());
    }

    @Test
    @Order(4)
    public void updatedPatientTest(){
        Patient patient = new Patient(null, "Albus","Potter", "75395146",
                LocalDate.of(2023, 04, 04), null, null);

        patientService.save(patient);

        patient.setName("Harry");

        Patient updatedPatient = patientService.update(patient);

        assertEquals("Harry", updatedPatient.getName());
    }

    @Test
    @Order(5)
    public void deletedPatientTest(){
        patientService.delete(1L);
        assertNull(patientService.findById(1L));
    }

    @Test
    @Order(6)
    public void whenIdIsNotInDataBase_thenExceptionIsThrown(){
        assertThrows(ResourceNotFoundException.class, ()-> patientService.findById(10L), "Patient not found with id 10");
    }

    @Test
    @Order(7)
    public void whenPatientNameIsEmpty_thenExceptionIsThrow(){
        Patient patient = new Patient(null, null,"Potter", "75395146",
                LocalDate.of(2023, 04, 04), null, null);

        assertThrows(BadRequestException.class, () -> patientService.save(patient), "Error, patient name, lastname and document must be complete");
    }

}