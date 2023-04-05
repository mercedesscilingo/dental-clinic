package com.example.dentalclinic.service.impl;

import com.example.dentalclinic.entity.Address;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.service.PatientService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
@Getter
@Setter
public class DataSet {   //TODO ver si usar

    @Autowired
    private PatientService patientService;


    public void generatingDataSet(){
        Address address1 = new Address(null, "Cabildo", "2589", "El Palomar", "Buenos Aires");
        Address address2 = new Address(null, "Santa Fe", "8899", "Villa Bosch", "Buenos Aires");
        Address address3 = new Address(null, "Callao", "1234", "Martin Coronado", "Buenos Aires");

        Patient patient1 = patientService.save( new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), address1, null));
        Patient patient2 =  patientService.save(new Patient(null, "Ron", "Weasley", "88592603",
                LocalDate.of(2023, 04, 04), address2, null));
        Patient patient3 = patientService.save( new Patient(null, "Harry", "Potter", "75395146",
                LocalDate.of(2023, 04, 04), address3, null));
    }


}
