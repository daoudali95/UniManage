package com.example.unimanagement.resource;

import com.example.unimanagement.dao.Roles;
import com.example.unimanagement.service.RolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/uni")
public class RolesResource {

    private final RolesService rolesService;

    public RolesResource(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping("/role")
    public ResponseEntity<Roles> saveRole(@RequestBody Roles role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(rolesService.saveRole(role));
    }

}