package com.courses.jpa.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @UpdateTimestamp // Specific to Hibernate not to JPA
    private LocalDateTime lastUpdatedDate;

    @CreationTimestamp // Specific to Hibernate not to JPA
    private LocalDateTime createdDate;

    public Course(String name) {
        this.name = name;
    }
}
