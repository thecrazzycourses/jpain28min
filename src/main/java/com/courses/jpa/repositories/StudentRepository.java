package com.courses.jpa.repositories;

import com.courses.jpa.entity.Course;
import com.courses.jpa.entity.Passport;
import com.courses.jpa.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Queue;

@Repository
@Slf4j
public class StudentRepository {

    EntityManager em;

    public StudentRepository(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }


    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        }else {
            em.merge(student);
        }
        return student;
    }

    public void deleteById(Long id) {
        // If no ID set then insert otherwise update
        Student student = findById(id);
        em.remove(student);
    }

    public List<Student> findAll() {
        TypedQuery<Student> namedQuery = em.createNamedQuery("find_all_students", Student.class);
        return namedQuery.getResultList();
    }


    @Transactional
    public void saveStudentWithPassport() {
        Passport passport = Passport.builder().number("E12345").build();
        em.persist(passport);

        Student student = Student.builder().name("Kuldeep").passport(passport).build();
        em.persist(student);
    }

    @Transactional
    public void addStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("Microservice");

        // insert into student & course table
        em.persist(student);
        em.persist(course);

        // relationship : insert into student_course table
        student.addCourse(course);
        course.addStudent(student);

        // persist owning side
        em.persist(student);

        // OR
        // Student student = new Student("Jack");
        // Course course = new Course("Microservice");
        // student.addCourse(course);
        // course.addStudent(student);
        // em.persist(student);
        // em.persist(course);
    }

    @Transactional
    public void saveStudent() {
        Student student = new Student("Jill");
        em.persist(student);
    }

    public void readOnlyStudent(long id) {
        Student student = em.find(Student.class, id);
        log.info("************************************");
        log.info("Only Student Details -> {}", student);
        log.info("************************************");
    }

    @Transactional
    public void readStudentWithPassport(long id) {
        Student student = em.find(Student.class, id);
        log.info("************************************");
        log.info("Only Student Details -> {}", student);
        log.info("Passport Details -> {}", student.getPassport());
        log.info("************************************");
    }

    @Transactional
    public void studentEnrollForCourse() {
        Student student = em.find(Student.class, 2004L);
        Course course = em.find(Course.class, 1001L);

        student.addCourse(course);
        //course.addStudent(student);

        em.persist(student);
    }

    @Transactional
    public void findAllCourseEnrollByStudent() {
        Student student = em.find(Student.class, 2004L);
        log.info("************************************");
        log.info("Courses -> {}", student.getCourses());
        log.info("************************************");
    }

    @Transactional
    public void findAllStudentsEnrollForCourse() {
        Course course = em.find(Course.class, 1001L);
        log.info("************************************");
        log.info("Students -> {}", course.getStudents());
        log.info("************************************");
    }

    public void findAllCourseWithoutStudent() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where c.students is empty ", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("************************************");
        log.info("Courses having no student-> {}", resultList);
        log.info("************************************");
    }

    public void findAllCourseWhereStudentsAreMoreThen2() {
        TypedQuery<Course> query = em.createQuery("select c from Course c where size(c.students) >= 2 ", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("************************************");
        log.info("Courses having more than 2 students-> {}", resultList);
        log.info("************************************");
    }

    public void findAllCourseOrderByStudents() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students)", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("************************************");
        log.info("Courses order by asc -> {}", resultList);
        log.info("************************************");
    }

    public void findAllCourseOrderByStudentsReverse() {
        TypedQuery<Course> query = em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
        List<Course> resultList = query.getResultList();
        log.info("************************************");
        log.info("Courses order by desc -> {}", resultList);
        log.info("************************************");
    }

    public void findPassportsLike() {
        TypedQuery<Student> query = em.createQuery("select s from Student s where s.passport.number like '%123%' ", Student.class);
        List<Student> resultList = query.getResultList();
        log.info("************************************");
        log.info("Student with passport pattern contains 123 -> {}", resultList);
        log.info("************************************");
    }

    public void join() {
        List<Object[]> resultList= em.createQuery("select c,s from Course c join c.students s").getResultList();
        log.info("ResultSize -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course{} Student{}", result[0], result[1]);
        }
    }

    public void leftJoin() {
        List<Object[]> resultList= em.createQuery("select c,s from Course c left join c.students s").getResultList();
        log.info("ResultSize -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Student{} Course{}", result[0], result[1]);
        }
    }

    public void crossJoin() {
        List<Object[]> resultList= em.createQuery("select c,s from Course c, Student s").getResultList();
        log.info("ResultSize -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Course{} Student{}", result[0], result[1]);
        }
    }

    public void join1() {
        List<Object[]> resultList= em.createQuery("select s,c,r from Student s join s.courses c join c.reviews r ").getResultList();
        log.info("ResultSize -> {}", resultList.size());
        for(Object[] result: resultList) {
            log.info("Student{} Course{} Review{}", result[0], result[1], result[2]);
        }
    }
}
