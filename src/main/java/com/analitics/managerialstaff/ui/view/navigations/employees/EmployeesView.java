package com.analitics.managerialstaff.ui.view.navigations.employees;

import com.analitics.managerialstaff.backend.model.Employee;
import com.vaadin.navigator.View;

import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
public interface EmployeesView extends View {
    String NAME = "employees";
    void setEmployeesList(Iterable<Employee> employees);
    void emptyEmployeeNotification();
    void saveEmployeeSuccessNotification();
    void editEmployeeSuccessNotification();
    void deleteEmployeeSuccessNotification();
}
