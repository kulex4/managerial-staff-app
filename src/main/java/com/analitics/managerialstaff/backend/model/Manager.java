package com.analitics.managerialstaff.backend.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author by nikolai.pashkevich
 */
@Data
@Entity
public class Manager {

    @Id
    @GeneratedValue
    private Long id;
}
