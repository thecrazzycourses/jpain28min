package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findById() {
        Course course = courseRepository.findById(1001L);
        assertEquals("Java", course.getName());
    }

    @Test
    @DirtiesContext // After this test is run, spring will rest the data so that data will be available for next test
    void deleteById() {
        courseRepository.deleteById(1001L);
        assertNull(courseRepository.findById(1001L));
    }

    @Test
    void save() {
        courseRepository.save(new Course("Test Course"));
        Course course = courseRepository.findById(2L);
        assertEquals("Test Course", course.getName());
    }

    @Test
    void update() {
        // Method start till end is within transaction boundary
        // We are creating new course and updating it within same transaction boundary
        // Entity Manager keeps track of transaction like create and update within same transaction boundary
        Course course = courseRepository.findById(1L);
        assertEquals("AngularJs", course.getName());

        courseRepository.save(new Course(1L, "AngularJs-Updated"));
        Course updatedCourse = courseRepository.findById(1L);
        assertEquals("AngularJs-Updated", updatedCourse.getName());

    }
}
