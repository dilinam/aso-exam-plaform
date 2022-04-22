package com.aso.examplatform.controller;

import com.aso.examplatform.model.Course;
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
    public ResponseEntity<Course> save(@Valid @RequestBody Course newCourse){
        return new ResponseEntity<>(courseService.create(newCourse), HttpStatus.CREATED);
    }

    @PutMapping(path = "")
    public ResponseEntity<Course> update(@Valid @RequestBody Course course) throws Exception{
        return new ResponseEntity<>(courseService.update(course), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(courseService.get(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable("id") Long id) throws Exception {
        courseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
