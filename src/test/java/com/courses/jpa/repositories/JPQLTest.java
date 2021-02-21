package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
public class JPQLTest {

    @Autowired
    EntityManager em;

    @Test
    void findAll_basic() {
        Query query = em.createQuery("SELECT c from Course c");
        List resultList = query.getResultList();
        log.info("ResultList -> {}", resultList);
    }

    @Test
    void findAll_typed() {
        TypedQuery<Course> query = em.createQuery("SELECT c from Course c", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("ResultList -> {}", resultList);
    }

    @Test
    void findAll_where() {
        TypedQuery<Course> query = em.createQuery("SELECT c from Course c where c.name like '%Java'", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("ResultList -> {}", resultList);
    }
}
