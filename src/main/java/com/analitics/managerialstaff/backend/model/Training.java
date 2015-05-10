package com.analitics.managerialstaff.backend.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Training {

    public static final String TRAINING_NAME = "trainingName";
    public static final String DATE_OF_TRAINING = "dateOfTraining";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String trainingName;

    @Column
    private Date dateOfTraining;

}
