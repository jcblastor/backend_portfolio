package com.portfolio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping
  public List<ProyectDto> getProyects() {
    return proyectService.getAllProyects();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProyectDto> getProyectById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(proyectService.getProyectById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProyectDto> updateProyect(@RequestBody ProyectDto proyectDto, @PathVariable(name = "id") long id) {
    ProyectDto proyectResp = proyectService.updateProyect(proyectDto, id);
    return new ResponseEntity<ProyectDto>(proyectResp, HttpStatus.OK);
  }
}
