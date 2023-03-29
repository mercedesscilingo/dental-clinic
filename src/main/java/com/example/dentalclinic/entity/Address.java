package com.example.dentalclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity (name = "addresses")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;
    private String street;
    private String number;
    private String town;
    private String province;


}
