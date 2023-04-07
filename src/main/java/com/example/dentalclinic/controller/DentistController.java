package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.DentistDto;
import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.service.DentistService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;
    private final Mapper mapper;


    @PostMapping()
    public ResponseEntity<DentistDto> save(@RequestBody DentistDto dentistDto) {

        Dentist dentist = mapper.toDentist(dentistDto);

        DentistDto response = mapper.toDentistDto(dentistService.save(dentist));


        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistDto> findById(@PathVariable(name = "id") String id) {

        DentistDto response = mapper.toDentistDto(dentistService.findById(Long.parseLong(id)));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DentistDto>> findAll(){

        return ResponseEntity.ok(dentistService.findAll().stream().map(dentist -> mapper.toDentistDto(dentist)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistDto> update(@RequestBody DentistDto dentistDto, @NonNull @PathVariable Long id) {

        Dentist dentist = mapper.toDentist(dentistDto);

        return ResponseEntity.ok(mapper.toDentistDto(dentistService.update(dentist)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        dentistService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The dentist has been removed");

    }


}
