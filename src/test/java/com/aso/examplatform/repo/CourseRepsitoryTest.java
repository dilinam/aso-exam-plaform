package com.aso.examplatform.repo;

import com.aso.examplatform.model.Course;
import com.aso.examplatform.model.Tenant;
import com.aso.examplatform.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CourseRepsitoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAllTest(){
        List<Course> courses = courseRepository.findAll();
        for (Course course:courses) {
            System.out.println(course);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        }

        Assertions.assertNotNull(courseRepository.findAll());
        Assertions.assertIterableEquals(courses,courseRepository.findAll());

    }
}
