package com.courses.jpa;

import com.courses.jpa.entity.FullTimeEmployee;
import com.courses.jpa.entity.PartTimeEmployee;
import com.courses.jpa.repositories.CourseRepository;
import com.courses.jpa.repositories.EmployeeRepository;
import com.courses.jpa.repositories.PassportRepository;
import com.courses.jpa.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
@Slf4j
public class JpaApplication implements CommandLineRunner {

    private StudentRepository studentRepository;
    private PassportRepository passportRepository;
    private CourseRepository courseRepository;
    private EmployeeRepository employeeRepository;

    public JpaApplication(StudentRepository studentRepository, PassportRepository passportRepository, CourseRepository courseRepository, EmployeeRepository employeeRepository) {
        this.studentRepository = studentRepository;
        this.passportRepository = passportRepository;
        this.courseRepository = courseRepository;
        this.employeeRepository = employeeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ////////////////////////////////////////////////////////////// ONE TO ONE MAPPING //////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // One To One : Student & Passport : Student is owning side

        /*
        // New Student joined but he don't have Passport yet
        studentRepository.saveStudent();
        // Later when student get passport then we will update his profile
        passportRepository.savePassport();
        // New Student joined he has have Passport
        studentRepository.saveStudentWithPassport();

        // Student visit his profile
        studentRepository.readOnlyStudent(2001L);
        // Student visit his profile and access his Passport
        studentRepository.readStudentWithPassport(2001L);
        // Student can search his Passport using passport number
        passportRepository.readOnlyPassport(3001L);
        */

        ////////////////////////////////////////////////////////////// ONE TO MANY MAPPING /////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // One To Many : Course & Reviews : 1 Course can have Many Reviews, Many side will be owning always in OneToMany. Reviews table will going to have course_id.

        /*
        // new course created
        courseRepository.createCourse();

        // student join the course : Many To Many Relation covered below studentEnrollForCourse()

        // student will provide review the course
        courseRepository.addReviewForCourse();

        // Load Course
        courseRepository.findCourse();

        // Load Reviews for a Course
        courseRepository.reviewsForCourse();

        // Get a Review using review id
        courseRepository.getReviewById();
        */

        ///////////////////////////////////////////////////////////// MANY TO MANY MAPPING /////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*
        // Many To Many : Student & Course

        // New Student joined : we already see this in OneToOne

        // New Course created : we already see this in OneToMany

        // Student enrol for a course
        studentRepository.studentEnrollForCourse();

        // Find all course enrolled by a student
        studentRepository.findAllCourseEnrollByStudent();

        // Find all students in a course
        studentRepository.findAllStudentsEnrollForCourse();

        // Find all courses : course repo
        // Find all students : student repo
        */

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // SINGLE_TABLE : Its Default, performance good as all data in 1 table, to many null
        /*
        employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal("1000")));
        log.info("Employees -> {}", employeeRepository.findAll());
        */


        // TABLE_PER_CLASS : Each concrete class have there own table, common column is repeated
        /*
        employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal("1000")));
        log.info("Employees -> {}", employeeRepository.findAll());
        */


        // JOINED :
        /*
        employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal("1000")));
        log.info("Employees -> {}", employeeRepository.findAll());
        */


        // Mapped Super Class:
        /*
        employeeRepository.save(new PartTimeEmployee("Jill", new BigDecimal("50")));
        employeeRepository.save(new FullTimeEmployee("Jack", new BigDecimal("1000")));
        log.info("Employees -> {}", employeeRepository.findAllPartTimeEmployee());
        log.info("Employees -> {}", employeeRepository.findAllFullTimeEmployee());
        */

        // Note : for performance prefer SINGLE_TABLE & for data integrity prefer JOINED

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // JPQL

        /*
        // Find all courses that do not have students : we write JPQL which uses relationship
        studentRepository.findAllCourseWithoutStudent();

        // Find courses who have more then 2 students enrolled
        studentRepository.findAllCourseWhereStudentsAreMoreThen2();

        // Find all courses in order with respect to students
        studentRepository.findAllCourseOrderByStudents();
        studentRepository.findAllCourseOrderByStudentsReverse();

        // Find all passport for students with passport pattern 1234
        studentRepository.findPassportsLike();

        // Use Joins :
        // Join : select c from Course c Join c.students s : This will give matched ones only
        studentRepository.join();

        // Left Join : select c from Course c LEFT Join c.students s : This will return all Courses which does not have students as well
        studentRepository.leftJoin();

        // Cross Join : select c from Course c, Student s : If 3 students and 4 course, 3 * 4 = 12 it will return 12 rows
        studentRepository.crossJoin();

        // Student ---> Course ---> Review
        studentRepository.join1();
        */

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Criteria Query
        /*
        // Get all the courses
        courseRepository.findAllCourses();
        */

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // ACID
        // Atomicity : Either all steps should be successful or rollback

        // Concurrency : Every transaction should leave system in consistent state
        //   ex transfer 100 from account a to b then balance should be consistent either transaction is successful or fail, not like deducted from a but not credited in b

        // Isolation : Is transaction a updated account a value to something then transaction b will see the updated value

        // Durability : If transaction is successful and system crash then change should be persisted

        // Isolation :
    }
}
