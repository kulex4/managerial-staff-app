package com.analitics.managerialstaff.ui.view.maincomponents;

import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class HeaderView extends MHorizontalLayout {

    @PostConstruct
    private void init() {
        MHorizontalLayout headerContent = new MHorizontalLayout()
                .withFullWidth()
                .withHeight("200px");

        addComponent(headerContent);
    }
}
