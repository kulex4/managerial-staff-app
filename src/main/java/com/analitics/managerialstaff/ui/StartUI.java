package com.analitics.managerialstaff.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.presenter.MainPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUI;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.SpringViewProvider;

/**
 * @author by nikolai.pashkevich
 */
@Title("Managerial Staff Application")
@Theme("valo")
@VaadinUI
@VaadinUIScope
public class StartUI extends UI {

    private static final long serialVersionUID = 1L;

    @Autowired
    private EventBus.UIEventBus eventBus;

    @Autowired
    private SpringViewProvider viewProvider;

    @Autowired
    private MainPresenter mainPresenter;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        eventBus.publish(EventScope.SESSION, this, Action.START);
        setContent(mainPresenter.getView());
    }
}