package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import com.courses.jpa.entity.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Slf4j
public class CourseRepository {

    EntityManager em;

    public CourseRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Course findById(Long id) {
        return em.find(Course.class, id);
    }


    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        }else {
            em.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        // If no ID set then insert otherwise update
        Course course = findById(id);
        em.remove(course);
    }

    public List<Course> findAll() {
        TypedQuery<Course> namedQuery = em.createNamedQuery("find_all_courses", Course.class);
        return namedQuery.getResultList();
    }


    public void play() {
        Course course = new Course();
        course.setName("Testing");
        em.persist(course);

        Course course1 = findById(1001L);
        course1.setName("Java - Updated");
    }

    @Transactional
    public void addReviewForCourse() {
        // get the course 1L
        Course course = findById(1L);

        // Create review
        Review review1 = new Review("Best Course", "5");
        Review review2 = new Review("Helpful Course", "4");

        // setting the relationship
        //course.addReview(review1);
        // review is owning side, need to tell for which course this review is
        review1.setCourse(course);
        review2.setCourse(course);
        //course.addReview(review2);


        // save to db
        em.persist(review1);
        em.persist(review2);
    }

    @Transactional
    public void createCourse() {
        Course course = new Course();
        course.setName("New Course Created for Hibernate");
        em.persist(course);
    }

    public void findCourse() {
        Course course = em.find(Course.class, 1L);
        log.info("**********************************");
        log.info("Course -> {}", course);
        log.info("**********************************");
    }

    @Transactional
    public void reviewsForCourse() {
        Course course = em.find(Course.class, 1L);
        log.info("**********************************");
        log.info("Course -> {}", course);
        log.info("Reviews -> {}", course.getReviews());
        log.info("**********************************");
    }

    public void getReviewById() {
        Review review = em.find(Review.class, 2L);
        log.info("**********************************");
        log.info("Review -> {}", review);
        log.info("**********************************");
    }

    public void findAllCourses() {
        // Select c from Course c , need to build this criteria

        // 1. Use criteria builder to create criteria query returning the expected result object
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in query
        Root<Course> courseRoot = cq.from(Course.class);

        // 3. Define predicates etc using criteria builder

        // 4. Add predicates etc to the criteria query

        // 5. Build TypedQuery using the entity manager and criteria query
        TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
        List<Course> resultList = query.getResultList();
        log.info("Typed Query {}", resultList);
    }
}
