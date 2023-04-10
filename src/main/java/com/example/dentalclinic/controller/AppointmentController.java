package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.AppointmentDto;
import com.example.dentalclinic.controller.dto.AppointmentRegistrationDto;
import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.entity.Appointment;

import com.example.dentalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    private final Mapper mapper;
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentRegistrationDto appointmentRegistrationDto){

        Appointment appointment = mapper.toAppointment(appointmentRegistrationDto) ;
        AppointmentDto response = mapper.toAppointmentDto(appointmentService.save(appointment));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> findById(@PathVariable(name = "id") String id) {

        AppointmentDto response = mapper.toAppointmentDto(appointmentService.findById(Long.parseLong(id)));

        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll().stream().map(a -> mapper.toAppointmentDto(a)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentRegistrationDto appointmentRegistrationDto, @NonNull @PathVariable Long id) {

        Appointment appointment = mapper.toAppointment(appointmentRegistrationDto);

        return ResponseEntity.ok(mapper.toAppointmentDto(appointmentService.update(appointment)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { //TODO: remover exception? aca tb paso string id y parseo? este metodo debe ser void?

        appointmentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The appointment has been removed");

    }

}
