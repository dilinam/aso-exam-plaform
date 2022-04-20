package com.aso.examplatform.repository;

import com.aso.examplatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
