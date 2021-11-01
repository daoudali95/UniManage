package com.example.unimanagement;

import com.example.unimanagement.dao.Roles;
import com.example.unimanagement.dao.Users;
import com.example.unimanagement.service.RolesService;
import com.example.unimanagement.service.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UniManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniManagementApplication.class, args);
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    CommandLineRunner run(UsersService usersService, RolesService rolesService){
//        return args -> {
//            rolesService.saveRole(new Roles(null, "ROLE_ADMIN"));
//            rolesService.saveRole(new Roles(null, "ROLE_TEACHER"));
//            rolesService.saveRole(new Roles(null, "ROLE_STUDENT"));
//            rolesService.saveRole(new Roles(null, "ROLE_LIBRARIAN"));
//
//            usersService.createUser(new Users(null, "daoud ali", "daoud", "123", new ArrayList<>()));
//            usersService.createUser(new Users(null, "ali ahmed", "ali", "123", new ArrayList<>()));
//            usersService.createUser(new Users(null, "imran khan", "imran", "123", new ArrayList<>()));
//            usersService.createUser(new Users(null, "david ali", "david", "123", new ArrayList<>()));
//
//            usersService.addRoleToUser("daoud", "ROLE_ADMIN");
//            usersService.addRoleToUser("ali", "ROLE_STUDENT");
//            usersService.addRoleToUser("imran", "ROLE_LIBRARIAN");
//            usersService.addRoleToUser("david", "ROLE_TEACHER");
//            usersService.addRoleToUser("david", "ROLE_STUDENT");
//
//        };
//    }

}
