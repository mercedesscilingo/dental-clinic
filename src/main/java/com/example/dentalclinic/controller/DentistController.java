package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.DentistDto;
import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.service.DentistService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


        return ResponseEntity.ok(response);

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

    @PutMapping()
    public ResponseEntity<DentistDto> update(@RequestBody DentistDto dentistDto) {

        Dentist dentist = mapper.toDentist(dentistDto);
        try{
            return ResponseEntity.ok(mapper.toDentistDto(dentistService.update(dentist)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        dentistService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The dentist has been removed");

    }


}
