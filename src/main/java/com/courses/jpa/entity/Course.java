package com.courses.jpa.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@NamedQueries(value = {
        @NamedQuery(name = "find_all_courses", query = "SELECT c FROM Course c") // we can pass multiple named queries comma separated
})
public class Course {

    @Id // Primary Key
    @GeneratedValue // This will start from 1, that's why in data.sql we start with 1001
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    @UpdateTimestamp // Specific to Hibernate not to JPA
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp // Specific to Hibernate not to JPA
    private LocalDateTime createdDate;

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
