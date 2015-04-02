package com.analitics.managerialstaff.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "main";

    public void setHeader(Component header) {

    }

    public void setMainMenu(Component mainMenu) {

    }

    public void setBody(Component body) {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }
}
