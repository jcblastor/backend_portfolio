package com.portfolio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portfolio.dtos.ProjectDto;
import com.portfolio.dtos.ProjectResponseDto;
import com.portfolio.exceptions.ResourceNotFoundException;
import com.portfolio.models.Project;
import com.portfolio.models.User;
import com.portfolio.repositories.ProjectRepository;
import com.portfolio.repositories.UserRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UserRepository userRepository;

  // convertir de modelo a Dto
  private ProjectDto convertDto(Project project) {
    // extraemos el usuario que viene en el pryecto
    User user = project.getUser();
    // mapeamos el dto
    ProjectDto projectDto = modelMapper.map(project, ProjectDto.class);
    // agregamos al dto el id del user
    projectDto.setUser_id(user.getId());
    
    return projectDto;
  }

  // convertir de Dto a modelo
  private Project convertModel(ProjectDto proyectDto) {
    Project proyect = modelMapper.map(proyectDto, Project.class);
    return proyect;
  }

  @Override
  public ProjectResponseDto getAllProyects(int numberPage, int limitPage) {
    Pageable pageable = PageRequest.of(numberPage, limitPage);
    Page<Project> projects = projectRepository.findAll(pageable);

    // buscamos todos los proyectos
    List<Project> proyectsList = projects.getContent();
    // convertimos los datos en un Dto para devolver
    List<ProjectDto> content = proyectsList.stream().map(proyect -> convertDto(proyect)).collect(Collectors.toList());

    // seteamos los datos para la paginacion
    ProjectResponseDto projectResp = new ProjectResponseDto();
    projectResp.setContent(content);
    projectResp.setNumberPage(projects.getNumber());
    projectResp.setLimitPage(projects.getSize());
    projectResp.setTotalProjects(projects.getTotalElements());
    projectResp.setTotalPage(projects.getTotalPages());
    projectResp.setLast(projects.isLast());

    return projectResp;
  }

  @Override
  public ProjectDto getProyectById(long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Project project = projectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    return convertDto(project);
  }

  @Override
  public ProjectDto createProject(ProjectDto projectDto) {
    // busco el usuario para asignarle el proyecto, si no lo encuentro devulevo error.
    User user = userRepository.findById(projectDto.getUser_id()).orElseThrow( ()  -> new ResourceNotFoundException("user", "id", projectDto.getUser_id()));

    // convierto el dto que recibo a un model para guardar en la db
    Project project = convertModel(projectDto);
    
    // asigno el proyecto al usuario
    project.setUser(user);

    // obtengo el proyecto guardado
    Project newProject = projectRepository.save(project);
    
    // convierto el proyecto guardado a Dto
    return convertDto(newProject);

  }

  @Override
  public ProjectDto updateProject(ProjectDto projectDto, long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Project project = projectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    // guardo los nuevos cambios
    project.setName(projectDto.getName());
    project.setDescription(projectDto.getDescription());
    project.setImage(projectDto.getImage());
    project.setLink_production(projectDto.getLink_production());
    project.setLink_github(projectDto.getLink_github());

    // guardo los datos en la db
    Project projectResp = projectRepository.save(project);

    // conviertos los datos guardados en la db a Dto y lo retorno
    return convertDto(projectResp);
  }

  @Override
  public void deleteProject(long id) {
    // busco el proyecto, si no lo encuentro lanzo un throw error
    Project project = projectRepository.findById(id).orElseThrow( ()  -> new ResourceNotFoundException("proyecto", "id", id));
    // si encuentro el proyecto lo elimino
    projectRepository.delete(project);
  }
}
