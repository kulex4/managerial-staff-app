package com.analitics.managerialstaff.backend.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String forename;

}
