package com.example.dentalclinic.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails save(UserDetails userdetails);

}
