package com.example.unimanagement.service;

import com.example.unimanagement.dao.Roles;
import com.example.unimanagement.dao.Users;
import com.example.unimanagement.repo.CoursesRepo;
import com.example.unimanagement.repo.RolesRepo;
import com.example.unimanagement.repo.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UsersService implements UserDetailsService {

    private final RolesRepo rolesRepo;
    private final UsersRepo usersRepo;
    private final CoursesRepo coursesRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepo.findByUsername(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.error("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }

    public Users createUser(Users user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setCreditHours(user.getCreditHours());
        return usersRepo.save(user);
    }

    public Optional<Users> findUser(Long id){return usersRepo.findById(id);}

    public Users getUser(String username) {
        log.info("Fetching user {}", username);
        return usersRepo.findByUsername(username);
    }

    public List<Users> getUsers() {
        log.info("Fetching all users");
        return usersRepo.findAll();
    }

    public void deleteUser(Long id){
        usersRepo.deleteById(id);
    }

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        Users user = usersRepo.findByUsername(username);
        Roles role = rolesRepo.findByName(roleName);
        user.getRoles().add(role);
    }

}