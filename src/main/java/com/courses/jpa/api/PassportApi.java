package com.courses.jpa.api;

import com.courses.jpa.entity.Passport;
import com.courses.jpa.entity.Student;
import com.courses.jpa.repositories.PassportRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportApi {

    private PassportRepository passportRepository;

    public PassportApi(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @GetMapping
    public List<Passport> findAll() {
        return passportRepository.findAll();
    }

    @GetMapping("/{id}")
    public Passport findById(@PathVariable Long id) {
        return passportRepository.findById(id);
    }
}
