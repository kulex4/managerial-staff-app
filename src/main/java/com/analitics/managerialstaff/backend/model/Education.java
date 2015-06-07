package com.analitics.managerialstaff.backend.model;

import com.analitics.managerialstaff.backend.model.enums.University;
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
public class Education {

    public static final String UNIVERSITY = "university";
    public static final String SPECIALITY = "speciality";
    public static final String STATUS = "status";
    public static final String DATE_OF_GRADUATION = "dateOfGraduation";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
            oldEmployee.removeEducation(this);
        }
        //set myself into new owner
        if (employee != null) {
            employee.addEducation(this);
        }
    }

    private boolean sameAsFormer(Employee newEmployee) {
        return employee == null ? newEmployee == null : employee.equals(newEmployee);
    }
}
