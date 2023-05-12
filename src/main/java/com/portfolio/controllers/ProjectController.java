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

import com.portfolio.dtos.ProjectDto;
import com.portfolio.dtos.ProjectResponseDto;
import com.portfolio.services.ProjectService;

@RestController
@RequestMapping("/api/proyects")
public class ProjectController {
  @Autowired
  private ProjectService projectService;

  @GetMapping
  public ProjectResponseDto getProyects(
      @RequestParam(value = "page", defaultValue = "0", required = false) int numberPage,
      @RequestParam(value = "limit", defaultValue = "10", required = false) int limitPage
    ) {
    return projectService.getAllProyects(numberPage, limitPage);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProjectDto> getProyectById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(projectService.getProyectById(id));
  }

  @PostMapping
  public ResponseEntity<ProjectDto> saveProyect(@RequestBody ProjectDto projectDto) {
    return new ResponseEntity<ProjectDto>(projectService.createProject(projectDto), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProjectDto> updateProyect(@RequestBody ProjectDto projectDto, @PathVariable(name = "id") long id) {
    ProjectDto proyectResp = projectService.updateProject(projectDto, id);
    return new ResponseEntity<ProjectDto>(proyectResp, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProyect(@PathVariable(name = "id")long id) {
    projectService.deleteProject(id);
    return new ResponseEntity<String>("El proyecto fue eliminado con exito", HttpStatus.OK);
  }
}
