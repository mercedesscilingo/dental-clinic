package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.controller.dto.PatientDto;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;

    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<PatientDto> save(@RequestBody PatientDto patientDto) {

        Patient patient = mapper.toPatient(patientDto);
        PatientDto response = mapper.toPatientDto(patientService.save(patient));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable Long id) {

        PatientDto response = mapper.toPatientDto(patientService.findById(id));

        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(){

        return ResponseEntity.ok(patientService.findAll().stream().map(patient -> mapper.toPatientDto(patient)).toList());
    }


    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto patientDto,@NonNull @PathVariable Long id) throws ResourceNotFoundException {

        Patient patient = mapper.toPatient(patientDto);

        return ResponseEntity.ok(mapper.toPatientDto(patientService.update(patient)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException{

        patientService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The patient has been removed");
    }




}
