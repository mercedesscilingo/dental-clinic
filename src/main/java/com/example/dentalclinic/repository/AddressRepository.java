package com.example.dentalclinic.repository;

import com.example.dentalclinic.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
