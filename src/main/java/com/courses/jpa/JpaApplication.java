package com.courses.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JpaApplication  {

    public static void main(String[] args) {

        SpringApplication.run(JpaApplication.class, args);

        /*ConfigurableApplicationContext run = SpringApplication.run(JpaApplication.class, args);
        PersonRepository repository = run.getBean(PersonRepository.class);

        Person person = new Person("Rahul", "Choudhary");
        repository.save(person);*/
    }

}
