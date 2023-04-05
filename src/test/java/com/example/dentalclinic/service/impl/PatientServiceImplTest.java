package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Address;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.service.PatientService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest
class PatientServiceImplTest {

    @Autowired
    private PatientService patientService;

    /*
    public void generatingDataSet(){
        Address address1 = new Address(null, "Cabildo", "2589", "El Palomar", "Buenos Aires");
        Address address2 = new Address(null, "Santa Fe", "8899", "Villa Bosch", "Buenos Aires");

        Patient patient1 = patientService.save( new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), address1, null));
        Patient patient2 =  patientService.save(new Patient(null, "Ron", "Weasley", "88592603",
                LocalDate.of(2023, 04, 04), address2, null));
    }
    */

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
    public void deletePatientTest(){
        patientService.delete(1L);
        assertTrue(patientService.findById(1L) == null);
    }

}