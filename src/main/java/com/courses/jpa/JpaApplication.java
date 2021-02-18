package com.courses.jpa;

import com.courses.jpa.entity.Course;
import com.courses.jpa.repositories.CourseRepository;
import com.courses.jpa.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JpaApplication implements CommandLineRunner {

    private PersonRepository personRepository;
    private CourseRepository courseRepository;

    public JpaApplication(PersonRepository personRepository, CourseRepository courseRepository) {
        this.personRepository = personRepository;
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	    /*

		// Find By ID
		log.info("Find Person with id 1 -> {}" , personRepository.findById(1));

		// Insert
		log.info("Insert new Person with id 1 -> {}" , personRepository.saveAndUpdate(new Person("Test User", "Test Location", new Date())));

		// Update
		log.info("Update Person with id 1001 -> {}" , personRepository.saveAndUpdate(new Person(1001, "Rahul", "Gurgoan", new Date())));

		// Delete
		personRepository.deleteById(1);

		// Find ALL
		log.info("Find All Person -> {}" , personRepository.findAll());
		*/


        // Courses
        log.info("Find All Courses -> {}" , courseRepository.findAll());
        log.info("Find Course By ID 1 -> {}" , courseRepository.findById(Long.valueOf(1001)));
        courseRepository.deleteById(Long.valueOf(1004));
        courseRepository.save(courseRepository.save(new Course("AngularJs")));
    }
}
