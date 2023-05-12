package com.portfolio.services;

import java.util.List;

import com.portfolio.dtos.ProyectDto;

public interface ProyectService {
  public ProyectDto createProyect(ProyectDto proyectDto);
  public List<ProyectDto> getAllProyects();
}
