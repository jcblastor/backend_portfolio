package com.portfolio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dtos.ProyectDto;
import com.portfolio.services.ProyectService;

@RestController
@RequestMapping("/api/proyects")
public class ProyectController {
  @Autowired
  private ProyectService proyectService;

  @PostMapping
  public ResponseEntity<ProyectDto> saveProyect(@RequestBody ProyectDto proyectDto) {
    return new ResponseEntity<ProyectDto>(proyectService.createProyect(proyectDto), HttpStatus.CREATED);
  }
}
