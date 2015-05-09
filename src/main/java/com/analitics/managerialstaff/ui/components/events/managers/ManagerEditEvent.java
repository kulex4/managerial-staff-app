package com.analitics.managerialstaff.ui.components.events.managers;

import com.analitics.managerialstaff.backend.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class ManagerEditEvent {
    private Employee selectedManager;

    public ManagerEditEvent(Employee selectedManager) {
        this.selectedManager = selectedManager;
    }
}
