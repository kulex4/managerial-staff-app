package com.analitics.managerialstaff.ui.components.events.managers;

import com.analitics.managerialstaff.backend.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * @author by nikolai.pashkevich
 */
@Getter
@Setter
public class ManagerSaveEvent {
    private boolean isNew = true;
    private Employee manager;

    public ManagerSaveEvent(Employee manager) {
        this(manager, true);
    }

    public ManagerSaveEvent(Employee manager, boolean isNew) {
        this.manager = manager;
        this.isNew = isNew;
    }
}
