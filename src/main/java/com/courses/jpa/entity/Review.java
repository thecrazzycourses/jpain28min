package com.courses.jpa.entity;

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
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Review {

    @Id // Primary Key
    @GeneratedValue // This will start from 1, that's why in data.sql we start with 1001
    @Setter(AccessLevel.NONE)
    private Long id;

    private String description;
    private String rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Course course;

    public Review(String description, String rating) {
        this.description = description;
        this.rating = rating;
    }
}
