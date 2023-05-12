package com.portfolio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.ProyectDto;
import com.portfolio.dtos.ProyectResponseDto;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.models.Proyect;
import com.portfolio.models.User;
import com.portfolio.repositories.ProyectRepository;
import com.portfolio.repositories.UserRepository;

@Service
public class ProyectServiceImpl implements ProyectService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ProyectRepository proyectRepository;

  @Autowired
  private UserRepository userRepository;

  // convertir de modelo a Dto
  private ProyectDto convertDto(Proyect proyect) {
    // extraemos el usuario que viene en el pryecto
    User user = proyect.getUser();
    // mapeamos el dto
    ProyectDto proyectDto = modelMapper.map(proyect, ProyectDto.class);
    // agregamos al dto el id del user
    proyectDto.setUser_id(user.getId());
    
    return proyectDto;
  }

  // convertir de Dto a modelo
  private Proyect convertModel(ProyectDto proyectDto) {
    Proyect proyect = modelMapper.map(proyectDto, Proyect.class);
    return proyect;
  }

  @Override
  public ProyectResponseDto getAllProyects(int numberPage, int limitPage) {
    Pageable pageable = PageRequest.of(numberPage, limitPage);
    Page<Proyect> proyects = proyectRepository.findAll(pageable);

    // buscamos todos los proyectos
    List<Proyect> proyectsList = proyects.getContent();
    // convertimos los datos en un Dto para devolver
    List<ProyectDto> content = proyectsList.stream().map(proyect -> convertDto(proyect)).collect(Collectors.toList());

    // seteamos los datos para la paginacion
    ProyectResponseDto proyectResp = new ProyectResponseDto();
    proyectResp.setContent(content);
    proyectResp.setNumberPage(proyects.getNumber());
    proyectResp.setLimitPage(proyects.getSize());
    proyectResp.setTotalProyects(proyects.getTotalElements());
    proyectResp.setTotalPage(proyects.getTotalPages());
    proyectResp.setLast(proyects.isLast());

    return proyectResp;
  }

  @Override
  public ProyectDto getProyectById(long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Proyect proyect = proyectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    return convertDto(proyect);
  }

  @Override
  public ProyectDto createProyect(ProyectDto proyectDto) {
    // busco el usuario para asignarle el proyecto, si no lo encuentro devulevo error.
    User user = userRepository.findById(proyectDto.getUser_id()).orElseThrow( ()  -> new ResourceNotFoundException("user", "id", proyectDto.getUser_id()));

    // convierto el dto que recibo a un model para guardar en la db
    Proyect proyect = convertModel(proyectDto);
    
    // asigno el proyecto al usuario
    proyect.setUser(user);

    // obtengo el proyecto guardado
    Proyect newProyect = proyectRepository.save(proyect);
    
    // convierto el proyecto guardado a Dto
    return convertDto(newProyect);

  }

  @Override
  public ProyectDto updateProyect(ProyectDto proyectDto, long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Proyect proyect = proyectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    // guardo los nuevos cambios
    proyect.setName(proyectDto.getName());
    proyect.setDescription(proyectDto.getDescription());
    proyect.setImage(proyectDto.getImage());
    proyect.setLink_production(proyectDto.getLink_production());
    proyect.setLink_github(proyectDto.getLink_github());

    // guardo los datos en la db
    Proyect proyectResp = proyectRepository.save(proyect);

    // conviertos los datos guardados en la db a Dto y lo retorno
    return convertDto(proyectResp);
  }

  @Override
  public void deleteProyect(long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Proyect proyect = proyectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    // si encuentro el proyecto lo elimino
    proyectRepository.delete(proyect);
  }
}
