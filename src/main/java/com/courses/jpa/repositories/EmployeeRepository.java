package com.courses.jpa.repositories;

import com.courses.jpa.entity.Employee;
import com.courses.jpa.entity.FullTimeEmployee;
import com.courses.jpa.entity.PartTimeEmployee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@Slf4j
public class EmployeeRepository {

    private EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public List<PartTimeEmployee> findAllPartTimeEmployee() {
        return em.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTimeEmployee() {
        return em.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }
}
