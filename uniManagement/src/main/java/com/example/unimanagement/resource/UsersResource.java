package com.example.unimanagement.resource;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.unimanagement.dao.Roles;
import com.example.unimanagement.dao.Users;
import com.example.unimanagement.dto.CoursesDto;
import com.example.unimanagement.security.GetToken;
import com.example.unimanagement.security.JwtTokenUtil;
import com.example.unimanagement.service.CoursesService;
import com.example.unimanagement.service.UsersService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/uni")
public class UsersResource {
    private final UsersService usersService;
    private final CoursesService coursesService;


    public UsersResource(UsersService usersService, CoursesService coursesService) {
        this.usersService = usersService;
        this.coursesService = coursesService;
    }

    @PostMapping("/user/save")
    public ResponseEntity<Users>saveUser(@RequestBody Users user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(usersService.createUser(user));
    }

    @DeleteMapping("/delUser/id")
    public ResponseEntity<String> userDelete(@PathVariable Long id){
        usersService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted of id :" + id);
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers(){
    return ResponseEntity.ok().body(usersService.getUsers());
}

//    @PostMapping("addCourses")
//    public ResponseEntity<UserCourses> UserCourses(@RequestBody CoursesDto coursesDto/*,Users users*/){
//        UserCourses coursesRes = coursesService.AddUserCourses(coursesDto/*, users*/);
//        return ResponseEntity.ok().body(coursesRes);
//    }

    @PostMapping("addCourses")
    public ResponseEntity<Users> UserCourses(@RequestBody CoursesDto coursesDto, String username /*, JwtTokenUtil jwtTokenUtil, GetToken getToken*/){
        Users users = coursesService.AddUserCourses(coursesDto, username/*,jwtTokenUtil,getToken*/);
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/user/{id}")
    public Optional<Users> retrieveUser(@PathVariable Long id){
        return usersService.findUser(id);
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUser form){
        usersService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Users user = usersService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Roles::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUser{
    private String username;
    private String roleName;
}