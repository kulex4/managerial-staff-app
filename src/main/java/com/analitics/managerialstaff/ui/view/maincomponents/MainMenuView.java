package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuView extends HorizontalLayout implements View {

    private MenuBar mainMenuBar;

    @PostConstruct
    private void init() {
        mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Home", null, null);

        addComponent(mainMenuBar);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
