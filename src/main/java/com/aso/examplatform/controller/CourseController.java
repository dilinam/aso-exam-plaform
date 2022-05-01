package com.aso.examplatform.controller;

import com.aso.examplatform.dto.CourseCandidatesRequest;
import com.aso.examplatform.model.Course;
import com.aso.examplatform.model.TenantUser;
import com.aso.examplatform.model.TenantUserCourse;
import com.aso.examplatform.model.User;
import com.aso.examplatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("")
    public List<Course> getAll(){
        return courseService.listAll();
    }

    @PostMapping(path = "")
    public ResponseEntity<Course> save(@Valid @RequestBody Course course){
        return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Course> update(@Valid @RequestBody Course course){
        try {
            return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(courseService.get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/candidate")
    public ResponseEntity<List<TenantUserCourse>> addCandidate(@Valid @RequestBody CourseCandidatesRequest courseCandidatesRequest){
        try {
            return new ResponseEntity<>(courseService.addCandidatesToCourse(courseCandidatesRequest), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable("id") Long id){
            try {
                courseService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<List<User>> getCandidateCourse(@PathVariable("courseId") Long courseId){
        try {
            return new ResponseEntity<>(courseService.getCandidates(courseId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
