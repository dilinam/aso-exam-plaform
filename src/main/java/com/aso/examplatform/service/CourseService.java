package com.aso.examplatform.service;

import com.aso.examplatform.dto.CourseCandidatesRequest;
import com.aso.examplatform.model.*;
import com.aso.examplatform.repository.CourseRepository;
import com.aso.examplatform.repository.TenantUserCourseRepository;
import com.aso.examplatform.repository.TenantUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TenantUserCourseRepository tenantUserCourseRepository;
    private final TenantUserRepository tenantUserRepository;

    public List<Course> listAll() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course create(Course course) {
        courseRepository.save(course);
        return course;
    }

    public Course update(Course course) throws Exception {
        if (courseRepository.findById(course.getCourseId()).isEmpty()) {
            throw new Exception("Course not found");
        }
        courseRepository.save(course);
        return course;
    }

    public Course get(Long id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        return result.orElseThrow(() -> new Exception("Course not found"));
    }

    public List<User> getCandidates(Long courseId){
        return tenantUserCourseRepository.findAllByCourseId(courseId);
    }

    public void delete(Long id) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            throw new Exception("Course not found");
        }
        Course course = courseOptional.get();
        course.setDeleted(true);
        courseRepository.save(course);
    }

    public List<TenantUserCourse> addCandidatesToCourse(CourseCandidatesRequest courseCandidatesRequest) throws Exception {
        Optional<Course> courseOptional = courseRepository.findById(courseCandidatesRequest.getCourseId());
        List<TenantUserCourse> tenantUserCourses = new ArrayList<>();
        if (courseOptional.isEmpty()) {
            throw new Exception("Course not found");
        } else {
            for (Long id : courseCandidatesRequest.getTenantUsers()) {
                tenantUserCourses.add(new TenantUserCourse(null,tenantUserRepository.findById(id).orElseThrow(() -> new Exception("User not found")), courseOptional.get(), false));
            }
            return tenantUserCourseRepository.saveAll(tenantUserCourses);
        }
    }
    public void removeCandidatesFromCourse(CourseCandidatesRequest courseCandidatesRequest) throws Exception{
        Course course = courseRepository.findById(courseCandidatesRequest.getCourseId()).orElseThrow(() -> new Exception("Course not found")); // get course or throw exception
        List<TenantUserCourse> tenantUserCourses = tenantUserCourseRepository.findTenantUserCourseByCourseId(courseCandidatesRequest.getCourseId()); // get tenant user courses by course id
        for(TenantUserCourse tenantUserCourse: tenantUserCourses){
            tenantUserCourse.setDeleted(true);
        }
        tenantUserCourseRepository.saveAll(tenantUserCourses);
    }

}
