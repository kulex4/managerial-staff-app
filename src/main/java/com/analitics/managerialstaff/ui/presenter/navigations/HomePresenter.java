package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.view.navigations.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class HomePresenter extends AbstractPresenter<HomeView> implements Serializable {

    @Autowired
    public HomePresenter(HomeView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }
}
