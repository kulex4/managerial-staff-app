package com.analitics.managerialstaff.ui.view.navigations.reporting;

import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.navigator.annotation.VaadinView;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = GenderAndAgeView.NAME)
public class GenderAndAgeViewImpl extends VerticalLayout implements GenderAndAgeView {

    @Autowired
    private NotificationManager notificationManager;

    @Autowired
    private EventBus.UIEventBus eventBus;

    @PostConstruct
    private void init() {
        setSizeFull();

        /*initComponents();
        initListeners();
        constructLayout();*/
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
