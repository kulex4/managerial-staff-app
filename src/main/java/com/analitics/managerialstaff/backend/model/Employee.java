package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.Gender;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Employee {

    public static final String SURNAME = "surname";
    public static final String FORENAME = "forename";
    public static final String AGE = "age";
    public static final String POSITION = "position";
    public static final String GENDER = "gender";
    public static final String GRADE = "grade";
    public static final String EXPERIENCE = "experience";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String surname;

    @Column(unique = true)
    private String forename;

    @Column
    private int age;

    @Column
    private String position;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column
    private int experience;

}
