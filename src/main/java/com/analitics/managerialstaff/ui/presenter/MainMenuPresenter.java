package com.analitics.managerialstaff.ui.presenter;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.view.MainMenuView;
import com.analitics.managerialstaff.ui.view.MainView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.spring.navigator.annotation.VaadinPresenter;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinPresenter(viewName = MainMenuView.VIEW_NAME)
public class MainMenuPresenter extends AbstractPresenter<MainMenuView> implements Serializable {

    @Autowired
    public MainMenuPresenter(MainMenuView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {
        UI.getCurrent().getNavigator().navigateTo(MainView.VIEW_NAME);
    }
}
