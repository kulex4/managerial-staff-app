package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class HeaderView extends MHorizontalLayout implements View {

    private MHorizontalLayout headerContent;

    @PostConstruct
    private void init() {
        headerContent = new MHorizontalLayout()
                .withFullWidth()
                .withHeight("200px");

        addComponent(headerContent);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
