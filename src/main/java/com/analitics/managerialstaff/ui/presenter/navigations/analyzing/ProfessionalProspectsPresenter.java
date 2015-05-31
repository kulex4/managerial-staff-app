package com.analitics.managerialstaff.ui.presenter.navigations.analyzing;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.analitics.managerialstaff.backend.service.employee.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.ProfessionalProspectsMenuCommand;
import com.analitics.managerialstaff.ui.components.events.EmployeesSelectEvent;
import com.analitics.managerialstaff.ui.view.navigations.analyzing.ProfessionalProspectsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class ProfessionalProspectsPresenter extends AbstractPresenter<ProfessionalProspectsView> implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public ProfessionalProspectsPresenter(ProfessionalProspectsView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onProfessionalProspectsMenuItemSelected(ProfessionalProspectsMenuCommand professionalProspectsMenuCommand) {

    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeesSelectEvent(EmployeesSelectEvent event) {
        Department department = event.getDepartment();
        Grade grade = event.getGrade();
        Iterable<Employee> employees = employeeService.findByDepartmentAndGrade(department, grade);
        getView().setEmployees(employees);
    }
}
