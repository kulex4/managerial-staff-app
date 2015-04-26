package com.analitics.managerialstaff.ui.presenter.maincomponents;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.components.events.NavigationRequest;
import com.analitics.managerialstaff.ui.presenter.navigations.EmployeesPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.HomePresenter;
import com.analitics.managerialstaff.ui.view.maincomponents.MainMenuView;
import com.analitics.managerialstaff.ui.view.navigations.HomeView;
import com.analitics.managerialstaff.ui.view.navigations.commands.HomeMenuCommand;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuPresenter extends AbstractPresenter<MainMenuView> implements Serializable {

    @Autowired
    private HomePresenter homePresenter;

    @Autowired private EmployeesPresenter employeesPresenter;

    @Autowired
    public MainMenuPresenter(MainMenuView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {
         UI.getCurrent().getNavigator().navigateTo(HomeView.NAME);
    }

    @EventBusListenerMethod
    public void onMainMenuSelection(NavigationRequest navigationRequest) {
        UI.getCurrent().getNavigator().navigateTo(navigationRequest.getNavigationState());
    }
}
