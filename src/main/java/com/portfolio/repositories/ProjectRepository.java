package com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long>{
  
}
