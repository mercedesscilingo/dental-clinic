package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.DentistDto;
import com.example.dentalclinic.controller.dto.Mappers;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.service.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final Mappers mapper;
    private final ObjectMapper objectMapper;

    @PostMapping()
    public ResponseEntity<DentistDto> save(@RequestBody DentistDto dentistDto) {
        //Dentist dentist = objectMapper.convertValue(dentistDto, Dentist.class);
        //DentistDto response = objectMapper.convertValue(dentistService.save(dentist), DentistDto.class);

        Dentist dentist = mapper.toDentist(dentistDto);

        DentistDto response = mapper.toDentistDto(dentistService.save(dentist));


        return ResponseEntity.ok(response);

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
