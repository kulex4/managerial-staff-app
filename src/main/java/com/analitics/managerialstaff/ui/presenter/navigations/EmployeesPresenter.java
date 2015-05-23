package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeEditEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeSaveEvent;
import com.analitics.managerialstaff.ui.view.navigations.employees.EmployeesView;
import com.analitics.managerialstaff.ui.components.commands.EmployeesMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.employees.forms.EmployeeAddEditForm;
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
    private EmployeeAddEditForm employeeAddEditForm;

    @Autowired
    public EmployeesPresenter(EmployeesView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onEmployeesMenuItemSelected(EmployeesMenuCommand employeesMenuCommand) {
        getView().setEmployeesList(employeeService.findSpecialists());
    }

    @EventBusListenerMethod
    private void onEmployeeAddEvent(EmployeeAddEvent employeeAddEvent) {
        employeeAddEditForm.setFormEntity(new Employee());
        employeeAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod
    private void onEmployeeEditEvent(EmployeeEditEvent employeeEditEvent) {
        employeeAddEditForm.setFormEntity(employeeEditEvent.getEmployee());
        employeeAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod
    private void onEmployeeDeleteEvent(EmployeeDeleteEvent employeeDeleteEvent) {
        employeeService.remove(employeeDeleteEvent.getEmployee());
        getView().deleteEmployeeSuccessNotification();
        getView().setEmployeesList(employeeService.findSpecialists());
    }

    @EventBusListenerMethod
    private void onEmployeeSaveEvent(EmployeeSaveEvent employeeSaveEvent) {
        employeeService.saveOrUpdate(employeeSaveEvent.getEmployee());
        if (employeeSaveEvent.isNew()) {
            getView().saveEmployeeSuccessNotification();
        } else {
            getView().editEmployeeSuccessNotification();
        }
        getView().setEmployeesList(employeeService.findSpecialists());
    }
}
