package com.analitics.managerialstaff.ui.components.events;

import com.analitics.managerialstaff.backend.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class EmployeeSaveEvent {
    private Employee employee;

    public EmployeeSaveEvent(Employee employee) {
        this.employee = employee;
    }
}
