package com.courses.jpa.repositories;

import com.courses.jpa.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PersonRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Person findById(Integer id) {
        return entityManager.find(Person.class, id);
    }


    public Person saveAndUpdate(Person person) {
        // If no ID set then insert otherwise update
        Person merge = entityManager.merge(person);
        return merge;
    }

    public void deleteById(Integer id) {
        // If no ID set then insert otherwise update
        Person person = findById(id);
        entityManager.remove(person);
    }

    public List<Person> findAll() {
        TypedQuery<Person> namedQuery = entityManager.createNamedQuery("find_all_person", Person.class);
        return namedQuery.getResultList();
    }


}
