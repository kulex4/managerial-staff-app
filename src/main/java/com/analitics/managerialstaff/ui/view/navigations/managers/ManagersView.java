package com.analitics.managerialstaff.ui.view.navigations.managers;

import com.analitics.managerialstaff.backend.model.Employee;
import com.vaadin.navigator.View;

/**
 * @author by nikolai.pashkevich
 */
public interface ManagersView extends View {
    String NAME = "managers";
    void setEmployeesList(Iterable<Employee> employees);
    void emptyEmployeeNotification();
    void saveEmployeeSuccessNotification();
    void editEmployeeSuccessNotification();
    void deleteEmployeeSuccessNotification();
}
