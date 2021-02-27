package com.courses.jpa.repositories;

import com.courses.jpa.entity.Passport;
import com.courses.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class PassportRepository {

    EntityManager em;

    public PassportRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Passport findById(Long id) {
        return em.find(Passport.class, id);
    }


    @Transactional
    public Passport save(Passport passport) {
        if (passport.getId() == null) {
            em.persist(passport);
        }else {
            em.merge(passport);
        }
        return passport;
    }

    public void deleteById(Long id) {
        // If no ID set then insert otherwise update
        Passport passport = findById(id);
        em.remove(passport);
    }

    public List<Passport> findAll() {
        TypedQuery<Passport> namedQuery = em.createNamedQuery("find_all_passports", Passport.class);
        return namedQuery.getResultList();
    }


    public void saveStudentWithPassport() {
        Passport passport = Passport.builder().number("E12345").build();
        em.persist(passport);

        Student student = Student.builder().name("Kuldeep").passport(passport).build();
        em.persist(student);
    }

    @Transactional
    public void savePassport() {
        Student student = em.find(Student.class, 1L);
        Passport passport = Passport.builder().number("G12345").build();

        student.setPassport(passport);

        if (passport.getId() == null) {
            em.persist(passport);
        }else {
            em.merge(passport);
        }
    }

    public void readOnlyPassport(long id) {
        Passport passport = em.find(Passport.class, id);
        log.info("************************************");
        log.info("Only Passport Details -> {}", passport);
        log.info("************************************");
    }
}
