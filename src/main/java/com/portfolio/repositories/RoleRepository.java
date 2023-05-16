package com.portfolio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  public Optional<Role> findByName(String name);
}
