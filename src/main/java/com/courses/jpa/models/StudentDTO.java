package com.courses.jpa.models;

import com.courses.jpa.entity.Passport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;

    private String name;

    private Passport passport;

    public StudentDTO(String name) {
        this.name = name;
    }

    public StudentDTO(Long id, String name) {
    }
}

