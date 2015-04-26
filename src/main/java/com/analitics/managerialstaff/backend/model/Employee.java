package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.Gender;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import lombok.Data;

import javax.persistence.*;

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

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String forename;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String position;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(nullable = false)
    private int experience;

}
