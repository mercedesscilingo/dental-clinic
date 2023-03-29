package com.example.dentalclinic.controller;

import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.service.PatientService;
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

    @PostMapping()
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.save(patient));
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
