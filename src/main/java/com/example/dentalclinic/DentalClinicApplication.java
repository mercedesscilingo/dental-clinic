package com.example.dentalclinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DentalClinicApplication {

	public static void main(String[] args) {
		ObjectMapper mapper = JsonMapper.builder() // or different mapper for other format
				.addModule(new ParameterNamesModule())
				.addModule(new Jdk8Module())
				.addModule(new JavaTimeModule())
				// and possibly other configuration, modules, then:
				.build();
		SpringApplication.run(DentalClinicApplication.class, args);
	}

}
