package com.example.dentalclinic.controller.dto;

public record PatientDto(String name, String lastname, String document, AddressDto address) {
    public record AddressDto(String street, String number, String town, String province){};

}
