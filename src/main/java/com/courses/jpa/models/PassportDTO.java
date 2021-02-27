package com.courses.jpa.models;

import com.courses.jpa.entity.Student;
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
public class PassportDTO {

    private Long id;

    private String number;

    private Student student;

    public PassportDTO(String number) {
        this.number = number;
    }
}
