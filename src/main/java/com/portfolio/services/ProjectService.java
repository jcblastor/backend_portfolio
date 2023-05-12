package com.portfolio.services;

import com.portfolio.dtos.ProjectDto;
import com.portfolio.dtos.ProjectResponseDto;

public interface ProjectService {
  public ProjectResponseDto getAllProyects(int numberPage, int limitPage);
  public ProjectDto getProyectById(long id);
  public ProjectDto createProject(ProjectDto projectDto);
  public ProjectDto updateProject(ProjectDto projectDto, long id);
  public void deleteProject(long id);
}
