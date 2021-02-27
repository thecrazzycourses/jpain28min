package com.courses.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class PartTimeEmployee extends Employee{

    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }
}
