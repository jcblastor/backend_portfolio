package com.portfolio.services;

import com.portfolio.dtos.ProyectDto;
import com.portfolio.dtos.ProyectResponseDto;

public interface ProyectService {
  public ProyectResponseDto getAllProyects(int numberPage, int limitPage);
  public ProyectDto getProyectById(long id);
  public ProyectDto createProyect(ProyectDto proyectDto);
  public ProyectDto updateProyect(ProyectDto proyectDto, long id);
  public void deleteProyect(long id);
}
