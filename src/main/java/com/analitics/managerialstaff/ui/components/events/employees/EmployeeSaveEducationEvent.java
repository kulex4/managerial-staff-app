package com.analitics.managerialstaff.ui.components.events.employees;

import com.analitics.managerialstaff.backend.model.Education;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class EmployeeSaveEducationEvent {
    private Education educationToSave;

    public EmployeeSaveEducationEvent(Education educationToSave) {
        this.educationToSave = educationToSave;
    }
}
