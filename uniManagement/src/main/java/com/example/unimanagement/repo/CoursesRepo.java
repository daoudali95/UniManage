package com.example.unimanagement.repo;

import com.example.unimanagement.dao.Courses;
import com.example.unimanagement.dao.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoursesRepo extends JpaRepository<Courses, Long> {
    Optional<Courses> findByName(String name);
    Optional<Courses> findByCreditHours(Integer creditHours);
}