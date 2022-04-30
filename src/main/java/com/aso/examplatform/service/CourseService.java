package com.aso.examplatform.service;

import com.aso.examplatform.model.Course;
import com.aso.examplatform.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> listAll(){
        return (List<Course>) courseRepository.findAll();
    }
    public Course create(Course course){
        courseRepository.save(course);
        return course;
    }
    public Course update(Course course) throws Exception{
        if (!courseRepository.findById(course.getCourseId()).isPresent()){
            throw new Exception("Course not found");
        }
        courseRepository.save(course);
        return course;
    }
    public Course get(Long id) throws Exception{
        Optional<Course> result = courseRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Course not found"));
    }
    public void delete(Long id) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (!courseOptional.isPresent()){
            throw new Exception("Course not found");
        }
        Course course = courseOptional.get();
        course.setDeleted(true);
        courseRepository.save(course);
    }
}
