package com.analitics.managerialstaff.backend.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String forename;

}
