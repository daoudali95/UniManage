package com.example.unimanagement.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;
//    private Integer creditHours;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Roles> roles = new ArrayList<>();

    @ManyToMany
    private Collection<Courses> courses = new ArrayList<>();

}