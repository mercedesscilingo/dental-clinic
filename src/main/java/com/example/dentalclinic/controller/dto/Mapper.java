package com.example.dentalclinic.controller.dto;

import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.entity.Patient;
import com.example.dentalclinic.service.DentistService;
import com.example.dentalclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Mapper {


    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    public PatientDto toPatientDto (Patient patient){

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patient.getName());
        patientDto.setLastname(patient.getLastname());
        patientDto.setDocument(patient.getDocument());
        patientDto.setAdmissionDate(patient.getAdmissionDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        patientDto.setAddress(patient.getAddress());
        patientDto.setAppointments(patient.getAppointments());

        return patientDto;

    }

    public Patient toPatient(PatientDto patientDto){

        Patient patient= new Patient();

        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setLastname(patientDto.getLastname());
        patient.setDocument(patientDto.getDocument());
        patient.setAddress(patientDto.getAddress());
        patient.setAppointments(patientDto.getAppointments());

        return patient;
    }


    public DentistDto toDentistDto (Dentist dentist){

        DentistDto dentistDto = new DentistDto();

        dentistDto.setId(dentist.getId());
        dentistDto.setName(dentist.getName());
        dentistDto.setLastname(dentist.getLastname());
        dentistDto.setLicense(dentist.getLicense());
        dentistDto.setAppointments(dentist.getAppointments());

        return dentistDto;
    }

    public Dentist toDentist (DentistDto dentistDto){

        Dentist dentist = new Dentist();

        dentist.setId(dentistDto.getId());
        dentist.setName(dentistDto.getName());
        dentist.setLastname(dentistDto.getLastname());
        dentist.setLicense(dentistDto.getLicense());
        dentist.setAppointments(dentistDto.getAppointments());

        return dentist;
    }

    public AppointmentDto toAppointmentDto (Appointment appointment){

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId(appointment.getId());
        appointmentDto.setDate(appointment.getDate().toString());
        appointmentDto.setPatient(appointment.getPatient());
        appointmentDto.setDentist(appointment.getDentist());

        return appointmentDto;
    }

    public Appointment toAppointment (AppointmentDto appointmentDto){

        Appointment appointment = new Appointment();

        appointment.setId(appointmentDto.getId());
        appointment.setDate(LocalDate.parse(appointmentDto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        appointment.setPatient(appointmentDto.getPatient());
        appointment.setDentist(appointmentDto.getDentist());

        return appointment;
    }

    public Appointment toAppointment (AppointmentRegistrationDto appointmentDto){

        Appointment appointment = new Appointment();

        appointment.setId(appointmentDto.getId());
        appointment.setDate(LocalDate.parse(appointmentDto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        Patient patient = patientService.findById(appointmentDto.getPatientId());
        appointment.setPatient(patient);

        Dentist dentist = dentistService.findById(appointmentDto.getDentistId());
        appointment.setDentist(dentist);

        return appointment;
    }


}
