package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.AppointmentDto;
import com.example.dentalclinic.controller.dto.Mapper;
import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.exceptions.ResourceNotFoundException;
import com.example.dentalclinic.service.AppointmentService;
import com.example.dentalclinic.service.DentistService;
import com.example.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentDto appointmentDto) throws BadRequestException {



        Appointment appointment = mapper.toAppointment(appointmentDto) ;

        AppointmentDto response = mapper.toAppointmentDto(appointmentService.save(appointment)); //TODO: ver exception especifica para bad request

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> findById(@PathVariable(name = "id") String id) {

        AppointmentDto response = mapper.toAppointmentDto(appointmentService.findById(Long.parseLong(id)));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll().stream().map(a -> mapper.toAppointmentDto(a)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto appointmentDto) {

        Appointment appointment = mapper.toAppointment(appointmentDto);

        return ResponseEntity.ok(mapper.toAppointmentDto(appointmentService.update(appointment)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { //TODO: remover exception? aca tb paso string id y parseo? este metodo debe ser void?

        appointmentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The appointment has been removed");

    }

}
