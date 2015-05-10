package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.Gender;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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
    public static final String CERTIFICATIONS = "certifications";
    public static final String EDUCATIONS = "educations";
    public static final String TRAININGS = "trainings";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="employee")
    private List<Certification> certifications;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="employee")
    private List<Education> educations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="employee")
    private List<Training> trainings;

}
