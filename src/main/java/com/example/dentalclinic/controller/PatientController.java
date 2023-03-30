package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.Mappers;
import com.example.dentalclinic.controller.dto.PatientDto;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;
    private final ObjectMapper objectMapper;
    private final Mappers mapper;

    @PostMapping()
    public ResponseEntity<PatientDto> save(@RequestBody PatientDto patientDto) {

        //Patient patient = objectMapper.convertValue(patientDto, Patient.class);
        //PatientDto response = objectMapper.convertValue(patientService.save(patient), PatientDto.class);

        Patient patient = mapper.toPatient(patientDto);
        PatientDto response = mapper.toPatientDto(patientService.save(patient));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> findById(@PathVariable String id) {

        return ResponseEntity.ok(patientService.findById(Long.parseLong(id)));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        List<Patient> patients= patientService.findAll();
        System.out.println(patients.toString());
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Patient>> update(@RequestBody Patient patient,@NonNull @PathVariable Long id) {

        ResponseEntity<Optional<Patient>> response = null;

        if (patientService.findById(id) != null)
            response = ResponseEntity.ok(patientService.update(patient));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (patientService.findById(id) != null) {
            patientService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("The patient has been removed");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

}
