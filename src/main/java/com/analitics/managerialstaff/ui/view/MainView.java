package com.analitics.managerialstaff.ui.view;

import com.analitics.managerialstaff.backend.model.Employee;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.navigator.annotation.VaadinView;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = MainView.VIEW_NAME)
public class MainView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "main";

    @PostConstruct
    private void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }

    public void setData() {
        Label label = new Label("Hello from view!");
        addComponent(label);
    }

    public void printEmployees(List<Employee> employees) {
        if (employees.size() > 0) {
            for (Employee employee : employees) {
                addComponent(new Label(employee.getSurname() + " " + employee.getForename()));
            }
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
