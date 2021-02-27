package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import com.courses.jpa.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    // One To Many Relation : Course & Review
    @Test
    @Transactional
    void findReviewsForCourse() {
        Course course = courseRepository.findById(1002L);
        log.info("Course -> {}", course);
        log.info("Reviews -> {}", course.getReviews());
    }

    @Test
    @Transactional
    void findCourseForReviews() {
        Review review = em.find(Review.class, 4003L);
        log.info("Review -> {}", review);
        log.info("Courses -> {}", review.getCourse());
    }

}
