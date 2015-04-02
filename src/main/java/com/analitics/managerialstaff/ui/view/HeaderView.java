package com.analitics.managerialstaff.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = HeaderView.VIEW_NAME)
public class HeaderView extends HorizontalLayout implements View {

    public static final String VIEW_NAME = "header";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
