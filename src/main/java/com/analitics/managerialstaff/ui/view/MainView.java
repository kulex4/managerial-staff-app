package com.analitics.managerialstaff.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "main";

    private Label label;

    @PostConstruct
    private void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setData() {
        label = new Label("Hello from view!");
        addComponent(label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
