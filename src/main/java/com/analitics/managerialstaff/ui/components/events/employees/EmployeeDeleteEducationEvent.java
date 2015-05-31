package com.analitics.managerialstaff.ui.components.events.employees;

import com.analitics.managerialstaff.backend.model.Education;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class EmployeeDeleteEducationEvent {
    private Education educationToDelete;

    public EmployeeDeleteEducationEvent(Education educationToDelete) {
        this.educationToDelete = educationToDelete;
    }
}
