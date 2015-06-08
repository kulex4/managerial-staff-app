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
@VaadinView(name = HomeView.NAME)
public class HomeViewImpl extends VerticalLayout implements HomeView {

    @PostConstruct
    private void init() {
        MHorizontalLayout homeContent = new MHorizontalLayout(
                new Label("Программная поддержка анализа деятельности управленческого персонала»  предназначена для усовершенствования процесса анализа деятельности сотрудников и руководителей подразделений, с помощью математических методов экономического анализа. Программный продукт полезен в  решении таких вопросов, как индивидуальный отбор, аттестация и ротация управленческого  персонала.")
        ).withFullHeight().withFullWidth().withMargin(true);
        addComponent(homeContent);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
