package com.portfolio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.ProyectDto;
import com.portfolio.models.Proyect;
import com.portfolio.repositories.ProyectRepository;

@Service
public class ProyectServiceImpl implements ProyectService {

  @Autowired
  private ProyectRepository proyectRepository;

  // convertir de modelo a Dto
  private ProyectDto convertDto(Proyect proyect) {
    ProyectDto proyectDto = new ProyectDto();
    proyectDto.setId(proyect.getId());
    proyectDto.setUser_id(proyect.getUser_id());
    proyectDto.setName(proyect.getName());
    proyectDto.setDescription(proyect.getDescription());
    proyectDto.setImage(proyect.getImage());
    proyectDto.setLink_production(proyect.getLink_production());
    proyectDto.setLink_github(proyect.getLink_github());

    return proyectDto;
  }

  // convertir de Dto a modelo
  private Proyect convertModel(ProyectDto proyectDto) {
    Proyect proyect = new Proyect();
    proyect.setUser_id(proyectDto.getUser_id());
    proyect.setName(proyectDto.getName());
    proyect.setDescription(proyectDto.getDescription());
    proyect.setImage(proyectDto.getImage());
    proyect.setLink_production(proyectDto.getLink_production());
    proyect.setLink_github(proyectDto.getLink_github());

    return proyect;
  }

  @Override
  public ProyectDto createProyect(ProyectDto proyectDto) {
    // convierto el dto que recibo a un model para guardar en la db
    Proyect proyect = convertModel(proyectDto);
    // obtengo el proyecto guardado
    Proyect newProyect = proyectRepository.save(proyect);
    // convierto el proyecto guardado a Dto
    ProyectDto proyectResp = convertDto(newProyect);
    // retorno el dto en el endpoint
    return proyectResp;
  }

  @Override
  public List<ProyectDto> getAllProyects() {
    // buscamos todos los proyectos
    List<Proyect> proyects = proyectRepository.findAll();
    // convertimos los datos en un Dto para devolver
    return proyects.stream().map(proyect -> convertDto(proyect)).collect(Collectors.toList());
  }
}
