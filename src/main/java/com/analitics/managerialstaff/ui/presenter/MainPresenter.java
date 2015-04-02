package com.analitics.managerialstaff.ui.presenter;

import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinPresenter(viewName = MainView.VIEW_NAME)
public class MainPresenter extends Presenter<MainView> implements Serializable {

    @Autowired
    private EmployeeService employeeService;

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().setData();
        getView().printEmployees(employeeService.findEmployees());
    }
}
