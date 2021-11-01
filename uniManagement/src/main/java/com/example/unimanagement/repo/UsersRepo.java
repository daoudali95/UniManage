package com.example.unimanagement.repo;

import com.example.unimanagement.dao.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByName(String name);
//    Users findByName(String name);

    @Query("SELECT user FROM Users user WHERE user.username = ?1")
    Users findByUsername(String  username);
//    Optional<Users> findByUsername(String  username);

}
