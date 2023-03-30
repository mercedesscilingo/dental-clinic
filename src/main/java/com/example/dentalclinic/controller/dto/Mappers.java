package com.example.dentalclinic.controller.dto;

import com.example.dentalclinic.entity.Appointment;
import com.example.dentalclinic.entity.Dentist;
import com.example.dentalclinic.entity.Patient;


public class Mappers {

    public PatientDto toPatientDto (Patient patient){

        PatientDto patientDto = new PatientDto();

        patientDto.setId(patient.getId());
        patientDto.setName(patientDto.getName());
        patientDto.setLastname(patientDto.getLastname());
        patientDto.setDocument(patientDto.getDocument());
        patientDto.setAdmissionDate(patient.getAdmissionDate());
        patientDto.setAddress(patientDto.getAddress());
        patientDto.setAppointments(patient.getAppointments());

        return patientDto;

    }

    public Patient toPatient(PatientDto patientDto){

        Patient patient= new Patient();

        patient.setId(patientDto.getId());
        patient.setName(patientDto.getName());
        patient.setLastname(patientDto.getLastname());
        patient.setDocument(patientDto.getDocument());
        patient.setAdmissionDate(patientDto.getAdmissionDate());
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
        appointmentDto.setDate(appointment.getDate());
        appointmentDto.setPatient(appointment.getPatient());
        appointmentDto.setDentist(appointment.getDentist());

        return appointmentDto;
    }

    public Appointment toAppointment (AppointmentDto appointmentDto){

        Appointment appointment = new Appointment();

        appointment.setId(appointmentDto.getId());
        appointment.setDate(appointmentDto.getDate());
        appointment.setPatient(appointmentDto.getPatient());
        appointment.setDentist(appointmentDto.getDentist());

        return appointment;
    }


}
