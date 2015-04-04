package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainView extends VerticalLayout implements View {

    @PostConstruct
    private void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setHeader(Component header) {
        addComponent(header);
    }

    public void setBody(Component body) {
        addComponent(body);
        setExpandRatio(body, 1);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
