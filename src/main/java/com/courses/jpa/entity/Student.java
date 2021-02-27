package com.courses.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@NamedQueries(value = {
        @NamedQuery(name = "find_all_students", query = "SELECT s FROM Student s") // we can pass multiple named queries comma separated
})
public class Student {

    @Id // Primary Key
    @GeneratedValue // This will start from 1, that's why in data.sql we start with 1001
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    //@JsonIgnore
    @ToString.Exclude
    private Passport passport;

    @ManyToMany()
    @JoinTable(name = "STUDENT_COURSE", joinColumns = @JoinColumn(name = "STUDENT_ID"),inverseJoinColumns = @JoinColumn(name = "COURSE_ID"))
    @ToString.Exclude
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
}
