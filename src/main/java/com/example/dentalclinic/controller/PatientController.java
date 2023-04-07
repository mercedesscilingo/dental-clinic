package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.controller.dto.PatientDto;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.service.PatientService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PatientDto> findById(@PathVariable String id) {
        Patient patient = patientService.findById(Long.parseLong(id));
        PatientDto response = mapper.toPatientDto(patient);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<List<PatientDto>> findAll(){
        List<Patient> patients = patientService.findAll();
        List<PatientDto> patients_dto = patients.stream().map(patient -> mapper.toPatientDto(patient)).toList();
        return ResponseEntity.ok(patients_dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto patientDto, @NonNull @PathVariable Long id) {

        Patient patient = mapper.toPatient(patientDto);

        return ResponseEntity.ok(mapper.toPatientDto(patientService.update(patient)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ResourceNotFoundException{ //TODO: remover exception?

        patientService.delete(Long.parseLong(id));

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The patient has been removed");
    }




}
