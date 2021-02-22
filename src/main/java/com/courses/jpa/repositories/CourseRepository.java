package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
}
