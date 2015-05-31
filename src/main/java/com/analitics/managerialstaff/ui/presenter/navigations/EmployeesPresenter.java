package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.service.EducationService;
import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.employees.*;
import com.analitics.managerialstaff.ui.view.navigations.employees.EmployeesView;
import com.analitics.managerialstaff.ui.components.commands.EmployeesMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.employees.forms.EmployeeAddEditForm;
import com.analitics.managerialstaff.ui.view.navigations.employees.forms.EmployeeAddEducationForm;
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
public class EmployeesPresenter extends AbstractPresenter<EmployeesView> implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private EmployeeAddEditForm employeeAddEditForm;

    @Autowired
    private EmployeeAddEducationForm employeeAddEducationForm;

    @Autowired
    public EmployeesPresenter(EmployeesView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeesMenuItemSelected(EmployeesMenuCommand employeesMenuCommand) {
        getView().setEmployeesList(employeeService.findSpecialists());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeAddEvent(EmployeeAddEvent event) {
        employeeAddEditForm.setFormEntity(new Employee());
        employeeAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeEditEvent(EmployeeEditEvent event) {
        employeeAddEditForm.setFormEntity(event.getEmployee());
        employeeAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeAddEducationEvent(EmployeeAddEducationEvent event) {
        //employeeService.lazyInitEducations(event.getEmployee());
        employeeAddEducationForm.setFormEntity(event.getEmployee());
        employeeAddEducationForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeDeleteEvent(EmployeeDeleteEvent event) {
        employeeService.remove(event.getEmployee());
        getView().deleteEmployeeSuccessNotification();
        getView().setEmployeesList(employeeService.findSpecialists());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeSaveEvent(EmployeeSaveEvent event) {
        employeeService.saveOrUpdate(event.getEmployee());
        if (event.isNew()) {
            getView().saveEmployeeSuccessNotification();
        } else {
            getView().editEmployeeSuccessNotification();
        }
        getView().setEmployeesList(employeeService.findSpecialists());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeSaveEducationEvent(EmployeeSaveEducationEvent event) {
        educationService.saveOrUpdate(event.getEducationToSave());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onEmployeeDeleteEducationEvent(EmployeeDeleteEducationEvent event) {
        educationService.remove(event.getEducationToDelete());
    }
}
