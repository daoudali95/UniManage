package com.example.unimanagement.service;

import com.example.unimanagement.dao.Roles;
import com.example.unimanagement.repo.RolesRepo;
import com.example.unimanagement.repo.UsersRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RolesService {

    private final RolesRepo rolesRepo;
    private final UsersRepo usersRepo;

    public RolesService(RolesRepo rolesRepo, UsersRepo usersRepo) {
        this.rolesRepo = rolesRepo;
        this.usersRepo = usersRepo;
    }

    public Roles saveRole(Roles role) {
        log.info("Saving new role {} to the database", role.getName());
        return rolesRepo.save(role);
    }

}