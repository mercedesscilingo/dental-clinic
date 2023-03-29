package com.example.dentalclinic.controller;

import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;

    @PostMapping()
    public ResponseEntity<Dentist> save(@RequestBody Dentist dentist) {

        return ResponseEntity.ok(dentistService.save(dentist));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dentist>> findById(@PathVariable(name = "id") String id) {

        return ResponseEntity.ok(dentistService.findById(Long.parseLong(id)));
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> findAll(){
        return ResponseEntity.ok(dentistService.findAll());
    }

    @PutMapping()
    public ResponseEntity<Optional<Dentist>> update(@RequestBody Dentist dentist) {

        ResponseEntity<Optional<Dentist>> response = null;

        if (dentist.getId() != null && dentistService.findById(dentist.getId()) != null)
            response = ResponseEntity.ok(dentistService.update(dentist));
        else
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;

        if (dentistService.findById(id) != null) {
            dentistService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("The dentist has been removed");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }







}
