package com.courses.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@NamedQueries(value = {
        @NamedQuery(name = "find_all_passports", query = "SELECT p FROM Passport p") // we can pass multiple named queries comma separated
})
public class Passport {

    @Id // Primary Key
    @GeneratedValue // This will start from 1, that's why in data.sql we start with 1001
    @Setter(AccessLevel.NONE)
    private Long id;

    private String number;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "passport")
    @JsonManagedReference
    @ToString.Exclude
    private Student student;

    public Passport(String number) {
        this.number = number;
    }
}
