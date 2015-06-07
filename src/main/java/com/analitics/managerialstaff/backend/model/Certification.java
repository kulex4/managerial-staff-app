package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.CertificationYear;
import com.analitics.managerialstaff.backend.model.enums.Quarter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author by nikolai.pashkevich
 */
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
@Entity
public class Certification {

    public static final String EMPLOYEE = "employee";
    public static final String EMPLOYEE_FORENAME = "employee.forename";
    public static final String EMPLOYEE_SURNAME = "employee.surname";
    public static final String EMPLOYEE_POSITION = "employee.position";
    public static final String YEAR = "year";
    public static final String QUARTER = "quarter";
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
    @Enumerated(EnumType.STRING)
    private CertificationYear year;

    @Column
    @Enumerated(EnumType.STRING)
    private Quarter quarter;

    @Column
    private float responsibility;

    @Column
    private float competence;

    @Column
    private float communicability;

    @Column
    private float testResult;

    public void setEmployee(Employee employee) {
        //prevent endless loop
        if (sameAsFormer(employee)) {
            return ;
        }
        //set new owner
        Employee oldEmployee = this.employee;
        this.employee = employee;
        //remove from the old owner
        if (oldEmployee != null) {
            oldEmployee.removeCertification(this);
        }
        //set myself into new owner
        if (employee != null) {
            employee.addCertification(this);
        }
    }

    private boolean sameAsFormer(Employee newEmployee) {
        return employee == null ? newEmployee == null : employee.equals(newEmployee);
    }
}
