package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.University;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Education {

    public static final String UNIVERSITY = "university";
    public static final String SPECIALITY = "speciality";
    public static final String STATUS = "status";
    public static final String DATE_OF_GRADUATION = "dateOfGraduation";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "education_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column
    @Enumerated(EnumType.STRING)
    private University university;

    @Column
    private String speciality;

    @Column
    private String status;

    @Column
    private Date dateOfGraduation;

}
