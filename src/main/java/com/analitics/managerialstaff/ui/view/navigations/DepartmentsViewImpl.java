package com.analitics.managerialstaff.ui.view.navigations;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = DepartmentsView.NAME)
public class DepartmentsViewImpl extends VerticalLayout implements DepartmentsView {

    @PostConstruct
    private void init() {
        MHorizontalLayout homeContent = new MHorizontalLayout(
                new Label("Departments view")
        ).withFullHeight().withFullWidth().withMargin(true);
        addComponent(homeContent);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
