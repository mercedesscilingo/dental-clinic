package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.AppointmentDto;
import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {


    private final AppointmentService appointmentService;

    private final ObjectMapper objectMapper;

    @PostMapping()
    public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentDto appointmentDto) { //TODO: ver validacion en el service (falta)

        Appointment appointment = objectMapper.convertValue(appointmentDto, Appointment.class);

        AppointmentDto response = objectMapper.convertValue(appointmentService.save(appointment), AppointmentDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AppointmentDto>> findById(@PathVariable(name = "id") String id) {

        AppointmentDto response = objectMapper.convertValue(appointmentService.findById(Long.parseLong(id)), AppointmentDto.class);

        return ResponseEntity.ok(Optional.ofNullable(response)); //Todo: ver optional
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDto>> findAll(){
        return ResponseEntity.ok(appointmentService.findAll().stream()
                .map(a -> objectMapper.convertValue(a, AppointmentDto.class)).toList());
    }

    @PutMapping()
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto appointmentDto) {

        Appointment appointment = objectMapper.convertValue(appointmentDto, Appointment.class);

        try{

            return ResponseEntity.ok(objectMapper.convertValue(appointmentService.update(appointment), AppointmentDto.class));

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
