package com.analitics.managerialstaff.backend.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Certification {

    public static final String EMPLOYEE = "employee";
    public static final String RESPONSIBILITY = "responsibility";
    public static final String COMPETENCE = "competence";
    public static final String COMMUNICABILITY = "communicability";
    public static final String TEST_RESULT = "testResult";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column
    private float responsibility;

    @Column
    private float competence;

    @Column
    private float communicability;

    @Column
    private float testResult;

}
