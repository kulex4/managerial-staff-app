package com.analitics.managerialstaff.backend.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @author by nikolai.pashkevich
 */
@Data
@EqualsAndHashCode(exclude = {"employee"})
@Entity
public class Training {

    public static final String TRAINING_NAME = "trainingName";
    public static final String DATE_OF_TRAINING = "dateOfTraining";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column
    private String trainingName;

    @Column
    private Date dateOfTraining;

}
