package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.AppointmentDto;
import com.example.dentalclinic.controller.dto.Mappers;
import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.service.AppointmentService;
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

    private final Mappers mapper;

    @PostMapping()
    public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentDto appointmentDto) { //TODO: ver validacion en el service (falta)

        Appointment appointment = mapper.toAppointment(appointmentDto) ;
        AppointmentDto response = mapper.toAppointmentDto(appointmentService.save(appointment));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> findById(@PathVariable(name = "id") Long id) {

        AppointmentDto response = mapper.toAppointmentDto(appointmentService.findById(id));

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll().stream().map(a -> mapper.toAppointmentDto(a)).toList());
    }

    @PutMapping()
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto appointmentDto) {

        Appointment appointment = mapper.toAppointment(appointmentDto);

        try{

            return ResponseEntity.ok(mapper.toAppointmentDto(appointmentService.update(appointment)));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        appointmentService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The appointment has been removed");

    }

}
