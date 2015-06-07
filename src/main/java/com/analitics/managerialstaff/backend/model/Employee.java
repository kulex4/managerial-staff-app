package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Gender;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@Data
@EqualsAndHashCode(exclude = {"certifications", "educations", "trainings"})
@ToString(exclude = {"certifications", "educations", "trainings"})
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
    public static final String DEPARTMENT = "department";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.ORDINAL)
    private Department department;

    @Column
    private int experience;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Certification> certifications;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Education> educations;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Training> trainings;

    public void addEducation(Education education) {
        //prevent endless loop
        if (educations.contains(education)) {
            return;
        }
        //add new account
        educations.add(education);
        //set myself into the twitter account
        education.setEmployee(this);
    }

    public void removeEducation(Education education) {
        //prevent endless loop
        if (!educations.contains(education)) {
            return;
        }
        //remove the account
        educations.remove(education);
        //remove myself from the twitter account
        education.setEmployee(null);
    }

    public void addCertification(Certification certification) {
        //prevent endless loop
        if (certifications.contains(certification)) {
            return;
        }
        //add new account
        certifications.add(certification);
        //set myself into the twitter account
        certification.setEmployee(this);
    }

    public void removeCertification(Certification certification) {
        //prevent endless loop
        if (!certifications.contains(certification)) {
            return;
        }
        //remove the account
        certifications.remove(certification);
        //remove myself from the twitter account
        certification.setEmployee(null);
    }
}
