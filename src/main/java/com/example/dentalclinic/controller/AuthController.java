package com.example.dentalclinic.controller;

import com.example.dentalclinic.controller.dto.AuthRequestDto;
import com.example.dentalclinic.exceptions.BadRequestException;
import com.example.dentalclinic.jwt.JwtUtils;
import com.example.dentalclinic.service.impl.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(user))
                    .build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto request) {
        try{
            if(userDetailsServiceImpl.loadUserByUsername(request.getUsername()) != null) throw new BadRequestException("User already exists.");
        }catch (UsernameNotFoundException err){
            //EMPTY
        }
        com.example.dentalclinic.entity.User user = new com.example.dentalclinic.entity.User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(userDetailsServiceImpl.save(user)))
                .build();
    }
}
