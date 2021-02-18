package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepository {

    EntityManager entityManager;

    public CourseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }


    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        }else {
            entityManager.merge(course);
        }
        return course;
    }

    public void deleteById(Long id) {
        // If no ID set then insert otherwise update
        Course course = findById(id);
        entityManager.remove(course);
    }

    public List<Course> findAll() {
        TypedQuery<Course> namedQuery = entityManager.createNamedQuery("find_all_courses", Course.class);
        return namedQuery.getResultList();
    }


}
