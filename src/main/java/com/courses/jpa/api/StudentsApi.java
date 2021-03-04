package com.courses.jpa.api;

import com.courses.jpa.entity.Student;
import com.courses.jpa.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentsApi {

    private StudentService studentService;

    public StudentsApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable String id) {
        return studentService.findById(Long.valueOf(id));
    }

}
