package com.example.unimanagement.service;

import com.example.unimanagement.dao.*;
import com.example.unimanagement.dto.CoursesDto;
import com.example.unimanagement.repo.CoursesRepo;
import com.example.unimanagement.repo.RolesRepo;
import com.example.unimanagement.repo.UsersRepo;
import com.example.unimanagement.security.GetToken;
import com.example.unimanagement.security.JwtTokenUtil;
import com.example.unimanagement.security.TokenWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CoursesService {

    @Autowired
    private TokenWrapper tokenWrapper;

    private final CoursesRepo coursesRepo;
    private final UsersRepo usersRepo;

//    public Integer creditHours = 0;

    public CoursesService(CoursesRepo coursesRepo,
                          UsersRepo usersRepo) {
        this.coursesRepo = coursesRepo;
        this.usersRepo = usersRepo;
    }

    public Courses createCourse(Courses course){return coursesRepo.save(course);}

//    public UserCourses AddUserCourses(CoursesDto coursesDto/*,Users users*/){
//        UserCourses userCourses = new UserCourses();
////        Users user2 = new Users();
//        var credit = new Object() {
//            Integer creditHours = 0;
//        };
////        user2.getCreditHours()
//
////        Optional<Users> user1 = usersRepo.findByName(coursesDto.getName());
////        if (user1.isPresent())
////            creditHours = user1.get().getCreditHours();
//
//        Optional<Users> user = usersRepo.findByName(coursesDto.getName());
//        if (user.isPresent())
//            userCourses.setUser(user.get());
//
//        Set<Courses> coursesSet = new HashSet<>();
////        for (String course : coursesDto.getCourses()){
////            Optional<Courses> coursesRes = coursesRepo.findByName(course);
////            if (coursesRes.isPresent())
////                coursesSet.add(coursesRes.get());
////                creditHours = creditHours + coursesRes.get().getCreditHours();
////                if (creditHours > 21){
////                    System.out.println("you exceed your credit hours limit");
////                    break;
////                }
////        }
//
//        coursesDto.getCourses().forEach((String course) ->{
//            Optional<Courses> coursesRes = coursesRepo.findByName(course);
//            if (coursesRes.isPresent())
//                coursesSet.add(coursesRes.get());
//
//            credit.creditHours = credit.creditHours + coursesRes.get().getCreditHours();
//
//            if (credit.creditHours > 21){
//                System.out.println("you exceed your credit hours limit");
////                break;
//            }
//        });
//        userCourses.setCourses(coursesSet);
//        userCourses.setCreditHours(credit.creditHours);
////        user2.setCreditHours(creditHours);
//
//        return userCoursesRepo.save(userCourses);
//    }

    public Users AddUserCourses(CoursesDto coursesDto,String username/*, JwtTokenUtil jwtTokenUtil, GetToken getToken*/){
//        Users user = new Users();

//        String username = jwtTokenUtil.getUsernameFromToken(getToken);
//
//        Users user1 = usersRepo.findByUsername(username);
//        user.setUsername(user1.getUsername());
        Users user = usersRepo.findByUsername(username);

        Set<Courses> coursesSet = new HashSet<>();
        coursesDto.getCourses().forEach((String course) ->{
            Optional<Courses> coursesRes = coursesRepo.findByName(course);
            if (coursesRes.isPresent())
                coursesSet.add(coursesRes.get());
        });

        user.setCourses(coursesSet);
        return usersRepo.save(user);
    }

    public Optional<Courses> findCourse(Long id){return coursesRepo.findById(id);}

    public List<Courses> getAllCourses(){return coursesRepo.findAll();}

    public void deleteCourse(Long id){
        coursesRepo.deleteById(id);
    }
}