package com.portfolio.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dtos.JwtAuthResponseDto;
import com.portfolio.dtos.LoginDto;
import com.portfolio.dtos.RegisterDto;
import com.portfolio.models.Role;
import com.portfolio.models.User;
import com.portfolio.repositories.RoleRepository;
import com.portfolio.repositories.UserRepository;
import com.portfolio.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @PostMapping("/login")
  public ResponseEntity<JwtAuthResponseDto> authUser(@RequestBody LoginDto loginDto) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    // obtenemos el token creado
    String token = jwtTokenProvider.generateToken(authentication);
    // devolvemos el token
    return ResponseEntity.ok(new JwtAuthResponseDto(token));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto) {
    if(userRepository.existsByEmail(registerDto.getEmail())) {
      return new ResponseEntity<>("El Correo electronico ya esta en uso.", HttpStatus.BAD_REQUEST);
    }

    User newUser = new User();
    newUser.setUsername(registerDto.getUsername());
    newUser.setEmail(registerDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Role roles = roleRepository.findByName("ADMIN").get();
    newUser.setRoles(Collections.singleton(roles));

    userRepository.save(newUser);
    return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
  }
}
