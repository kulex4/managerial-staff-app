package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerAddEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerEditEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerSaveEvent;
import com.analitics.managerialstaff.ui.components.commands.ManagersMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.managers.ManagersView;
import com.analitics.managerialstaff.ui.view.navigations.managers.forms.ManagerAddEditForm;
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
public class ManagersPresenter extends AbstractPresenter<ManagersView> implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerAddEditForm managerAddEditForm;

    @Autowired
    public ManagersPresenter(ManagersView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onManagersMenuItemSelected(ManagersMenuCommand managersMenuCommand) {
        getView().setEmployeesList(employeeService.findManagers());
    }

    @EventBusListenerMethod
    private void onManagerAddEvent(ManagerAddEvent managerAddEvent) {
        managerAddEditForm.setFormEntity(new Employee());
        managerAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod
    private void onManagerEditEvent(ManagerEditEvent managerEditEvent) {
        managerAddEditForm.setFormEntity(managerEditEvent.getSelectedManager());
        managerAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod
    private void onManagerDeleteEvent(ManagerDeleteEvent managerDeleteEvent) {
        employeeService.remove(managerDeleteEvent.getSelectedManager());
        getView().deleteEmployeeSuccessNotification();
        getView().setEmployeesList(employeeService.findManagers());
    }

    @EventBusListenerMethod
    private void onManagerSaveEvent(ManagerSaveEvent managerSaveEvent) {
        employeeService.saveOrUpdate(managerSaveEvent.getManager());
        if (managerSaveEvent.isNew()) {
            getView().saveEmployeeSuccessNotification();
        } else {
            getView().editEmployeeSuccessNotification();
        }
        getView().setEmployeesList(employeeService.findManagers());
    }
}
