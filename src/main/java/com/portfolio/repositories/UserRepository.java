package com.portfolio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.portfolio.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
