package com.analitics.managerialstaff.ui.components.events.employees;

import com.analitics.managerialstaff.backend.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class EmployeeAddEducationEvent {
    private Employee employee;

    public EmployeeAddEducationEvent(Employee employee) {
        this.employee = employee;
    }
}
