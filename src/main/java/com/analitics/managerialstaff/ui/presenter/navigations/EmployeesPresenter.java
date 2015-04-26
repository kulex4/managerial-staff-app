package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.EmployeeEditEvent;
import com.analitics.managerialstaff.ui.components.events.EmployeeSaveEvent;
import com.analitics.managerialstaff.ui.view.navigations.EmployeesView;
import com.analitics.managerialstaff.ui.view.navigations.commands.EmployeesMenuCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class EmployeesPresenter extends AbstractPresenter<EmployeesView> implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public EmployeesPresenter(EmployeesView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onEmployeesMenuItemSelected(EmployeesMenuCommand employeesMenuCommand) {
        getView().setEmployeesList(employeeService.findEmployees());
    }

    @EventBusListenerMethod
    private void onEmployeeAddEvent(EmployeeAddEvent employeeAddEvent) {
        //todo open add employee form
    }

    @EventBusListenerMethod
    private void onEmployeeEditEvent(EmployeeEditEvent employeeEditEvent) {
        //todo open edit employee form
    }

    @EventBusListenerMethod
    private void onEmployeeDeleteEvent(EmployeeDeleteEvent employeeDeleteEvent) {
        employeeService.remove(employeeDeleteEvent.getEmployee());
        //todo ui notification
    }

    @EventBusListenerMethod
    private void onEmployeeSaveEvent(EmployeeSaveEvent employeeSaveEvent) {
        employeeService.saveOrUpdate(employeeSaveEvent.getEmployee());
        //todo ui notification
    }
}
