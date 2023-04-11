package com.example.dentalclinic.service.impl;


import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DentistServiceImplTest {

    @Autowired
    DentistServiceImpl dentistService;

    @Test
    @Order(1)
    public void saveDentistTest(){
        Dentist dentist = dentistService.save(new Dentist(null, "Albus", "Dumbledore", "582369", null));

        assertEquals(1L, dentist.getId());
    }

    @Test
    @Order(2)
    public void findByIdDentistTest(){
        assertNotNull(dentistService.findById(1L).getId());
    }

    @Test
    @Order(3)
    public void findAllDentistsTest(){

        Dentist dentist = dentistService.save( new Dentist(null, "Albus", "Dumbledore", "582369", null));

        List<Dentist> dentistList = dentistService.findAll();

        assertFalse(dentistList.isEmpty());
    }

    @Test
    @Order(4)
    public void updatedDentistTest(){
        Dentist dentist = new Dentist(null, "Albus", "Dumbledore", "582369", null);

        dentistService.save(dentist);

        dentist.setName("Severus");

        Dentist updatedDentist = dentistService.update(dentist);

        assertEquals("Severus", updatedDentist.getName());
    }

    @Test
    @Order(5)
    public void deletedDentistTest(){
        Long id = 1L;

        dentistService.delete(id);

        assertThrows(ResourceNotFoundException.class, ()->dentistService.findById(1L));
    }

    @Test
    @Order(6)
    public void whenIdIsNotInDataBase_thenExceptionIsThrown(){
        assertThrows(ResourceNotFoundException.class, ()-> dentistService.findById(10L), "" +
                "Dentist not found with id 10");
    }

    @Test
    @Order(7)
    public void whenDentistNameIsEmpty_thenExceptionIsThrow(){
        Dentist dentist = new Dentist(null, null, "Dumbledore", "582369", null);

        assertThrows(BadRequestException.class, () -> dentistService.save(dentist), "Dentist name, lastname and license must be complete");
    }







}