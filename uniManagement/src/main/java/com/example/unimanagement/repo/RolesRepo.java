package com.example.unimanagement.repo;

import com.example.unimanagement.dao.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepo extends JpaRepository<Roles, Long> {
//   Optional<Roles> findByName(String name);
   Roles findByName(String name);
//   Optional <Roles> findByName(List<String> name);
}
