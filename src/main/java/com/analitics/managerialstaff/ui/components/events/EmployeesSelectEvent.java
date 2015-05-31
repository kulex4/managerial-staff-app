package com.analitics.managerialstaff.ui.components.events;

import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class EmployeesSelectEvent {
    private Grade grade;
    private Department department;

    public EmployeesSelectEvent(Department department, Grade grade) {
        this.department = department;
        this.grade = grade;
    }
}
