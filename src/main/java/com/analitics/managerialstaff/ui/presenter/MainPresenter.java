package com.analitics.managerialstaff.ui.presenter;

import com.analitics.managerialstaff.backend.service.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.navigator.Presenter;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinPresenter(viewName = MainView.VIEW_NAME)
public class MainPresenter extends AbstractPresenter<MainView> implements Serializable {

    @Autowired
    private HeaderPresenter headerPresenter;

    @Autowired
    private MainMenuPresenter mainMenuPresenter;

    @Autowired
    private BodyPresenter bodyPresenter;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    public MainPresenter(MainView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {
        getView().setHeader(headerPresenter.getView());
        getView().setMainMenu(mainMenuPresenter.getView());
        getView().setBody(bodyPresenter.getView());

        /*getView().setData();
        getView().printEmployees(employeeService.findEmployees());*/
    }
}
