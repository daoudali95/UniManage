package com.example.unimanagement.repo;

import com.example.unimanagement.dao.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepo extends JpaRepository<Books, Long> {
    Optional<Books> findByName(String Name);

}
