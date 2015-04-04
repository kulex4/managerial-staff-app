package com.analitics.managerialstaff.ui.view.navigations;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = HomeView.NAME)
public class HomeViewImpl extends VerticalLayout implements HomeView {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
