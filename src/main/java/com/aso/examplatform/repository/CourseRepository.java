package com.aso.examplatform.repository;

import com.aso.examplatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByDeleted(boolean deleted);
}
