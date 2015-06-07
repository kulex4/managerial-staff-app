package com.analitics.managerialstaff.ui.view.navigations.certification.dto;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CertificationDTO {

    public static final String SURNAME = "surname";
    public static final String FORENAME = "forename";
    public static final String POSITION = "position";
    public static final String YEAR = "year";
    public static final String QUARTER = "quarter";
    public static final String RESPONSIBILITY = "responsibility";
    public static final String COMPETENCE = "competence";
    public static final String COMMUNICABILITY = "communicability";
    public static final String TEST_RESULT = "testResult";

    private Long id;
    private String surname;
    private String forename;
    private String position;
    private CertificationYear year;
    private Quarter quarter;
    private float responsibility;
    private float competence;
    private float communicability;
    private float testResult;

    public CertificationDTO(Certification certification) {
        this.id = certification.getId();
        this.surname = certification.getEmployee().getSurname();
        this.forename = certification.getEmployee().getForename();
        this.position = certification.getEmployee().getPosition();
        this.year = certification.getYear();
        this.quarter = certification.getQuarter();
        this.responsibility = certification.getResponsibility();
        this.competence = certification.getCompetence();
        this.communicability = certification.getCommunicability();
        this.testResult = certification.getTestResult();
    }
}
