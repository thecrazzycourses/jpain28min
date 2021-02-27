package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import com.courses.jpa.entity.Passport;
import com.courses.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    PassportRepository passportRepository;

    @Autowired
    EntityManager em;

    // One To One Relation : Student & Passport
    @Test
    @Transactional
    void StudentRepo() {
        Student student = studentRepository.findById(2001L);
        log.info("Student -> {}", student);
        log.info("Student passport -> {}", student.getPassport());
    }

    @Test
    @Transactional
    void passportRepo() {
        Passport passport = passportRepository.findById(3001L);
        log.info("Passport -> {}", passport);
        log.info("Passport for Student -> {}", passport.getStudent());
    }


    // Many To Many Relation : Student & Courses
    @Test
    @Transactional
    void findStudentForCourseTest() {
        Student student = em.find(Student.class, 2001L);
        log.info("Student -> {}", student);
        log.info("Courses -> {}", student.getCourses());
    }

    @Test
    @Transactional
    void findCourseForStudentTest() {
        Course course = em.find(Course.class, 1001L);
        log.info("Course -> {}", course);
        log.info("Students -> {}", course.getStudents());
    }
}
