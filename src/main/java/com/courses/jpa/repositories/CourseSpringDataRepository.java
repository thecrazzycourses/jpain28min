package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String name);
    List<Course> deleteByName(String name);
    Long countByName(String name);
    List<Course> findByNameOrderById(String name);

    // JPQL
    @Query("select c from Course c where c.name like '%Java%'")
    List<Course> findCourseWhereJava();

    // NATIVE QUERY
    @Query(value = "select * from Course c where name like '%Java%'", nativeQuery = true)
    List<Course> findCourseWhereJavaNativeQuery();

    // NAMED QUERY
    @Query(name = "find_all_courses")
    List<Course> findAllCoursesNamedQuery();
}
