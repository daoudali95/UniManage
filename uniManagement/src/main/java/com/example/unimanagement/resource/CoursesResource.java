package com.example.unimanagement.resource;

import com.example.unimanagement.dao.Courses;
import com.example.unimanagement.service.CoursesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/uni")
public class CoursesResource {
    private final CoursesService coursesService;

    public CoursesResource(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @PostMapping("course")
    public ResponseEntity<Courses> courseCreate(@RequestBody Courses courses){
        Courses coursesRes = coursesService.createCourse(courses);
        return ResponseEntity.ok().body(coursesRes);
    }

    @GetMapping("Allcourses")
    public ResponseEntity<List<Courses>>allCourses(){
        List<Courses> courses = coursesService.getAllCourses();
        return ResponseEntity.ok().body(courses);
    }

    @GetMapping("/course/{id}")
    public Optional<Courses> retrieveCourse(@PathVariable Long id){
        return coursesService.findCourse(id);
    }

    @DeleteMapping("/delCourse/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        coursesService.deleteCourse(id);
        return ResponseEntity.ok().body("Course deleted id : "+ id);
    }
}
