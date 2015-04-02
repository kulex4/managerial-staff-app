package com.analitics.managerialstaff.ui.presenter;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.view.HeaderView;
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
@VaadinPresenter(viewName = HeaderView.VIEW_NAME)
public class HeaderPresenter extends AbstractPresenter<HeaderView> implements Serializable {

    @Autowired
    public HeaderPresenter(HeaderView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {

    }
}
