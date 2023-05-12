package com.portfolio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.dtos.ProyectDto;
import com.portfolio.dtos.ProyectResponseDto;
import com.portfolio.services.ProyectService;

@RestController
@RequestMapping("/api/proyects")
public class ProyectController {
  @Autowired
  private ProyectService proyectService;

  @GetMapping
  public ProyectResponseDto getProyects(
      @RequestParam(value = "page", defaultValue = "0", required = false) int numberPage,
      @RequestParam(value = "limit", defaultValue = "10", required = false) int limitPage
    ) {
    return proyectService.getAllProyects(numberPage, limitPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProyectDto> getProyectById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(proyectService.getProyectById(id));
  }

  @PostMapping
  public ResponseEntity<ProyectDto> saveProyect(@RequestBody ProyectDto proyectDto) {
    return new ResponseEntity<ProyectDto>(proyectService.createProyect(proyectDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProyectDto> updateProyect(@RequestBody ProyectDto proyectDto, @PathVariable(name = "id") long id) {
    ProyectDto proyectResp = proyectService.updateProyect(proyectDto, id);
    return new ResponseEntity<ProyectDto>(proyectResp, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProyect(@PathVariable(name = "id")long id) {
    proyectService.deleteProyect(id);
    return new ResponseEntity<String>("El proyecto fue eliminado con exito", HttpStatus.OK);
  }
}
