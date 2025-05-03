package com.api.api.repositories.user;

import com.api.api.entities.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  boolean existsByUsername(String username);

  Optional<Admin> findByUsername(String username);
}