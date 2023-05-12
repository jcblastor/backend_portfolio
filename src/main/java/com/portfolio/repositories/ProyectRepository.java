package com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.models.Proyect;

public interface ProyectRepository extends JpaRepository<Proyect, Long>{
  
}
