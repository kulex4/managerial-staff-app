package com.analitics.managerialstaff.ui.view.navigations;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.components.events.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.EmployeeEditEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = EmployeesView.NAME)
public class EmployeesViewImpl extends VerticalLayout implements EmployeesView {

    @Autowired
    private EventBus.UIEventBus eventBus;

    private Label viewNameLabel;
    private MHorizontalLayout controlButtonsLayout;
    private Button addEmployeeButton;
    private Button deleteEmployeeButton;
    private Button editEmployeeButton;
    private Grid employeesGrid;
    private BeanItemContainer<Employee> employeeContainer;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        initListeners();
        constructLayout();
    }

    private void initComponents() {
        viewNameLabel = new Label("Список всех сотрудников");
        viewNameLabel.setStyleName(MyTheme.LABEL_H2);
        viewNameLabel.addStyleName(MyTheme.LABEL_NO_MARGIN);

        addEmployeeButton = new MButton("Добавить").withIcon(FontAwesome.PLUS);
        editEmployeeButton = new MButton("Изменить").withIcon(FontAwesome.PENCIL);
        deleteEmployeeButton = new MButton("Удалить").withIcon(FontAwesome.TRASH_O);

        controlButtonsLayout = new MHorizontalLayout(
                addEmployeeButton,
                editEmployeeButton,
                deleteEmployeeButton
        ).withMargin(false);

        employeeContainer = new BeanItemContainer<>(Employee.class);
        employeesGrid = new Grid(employeeContainer);
        employeesGrid.setWidth("99%");
        employeesGrid.setColumnOrder(
                Employee.SURNAME,
                Employee.FORENAME,
                Employee.GENDER,
                Employee.AGE,
                Employee.EXPERIENCE,
                Employee.POSITION,
                Employee.GRADE
        );
        employeesGrid.removeColumn("id");
    }

    private void initListeners() {
        addEmployeeButton.addClickListener(clickEvent ->
                eventBus.publish(EventScope.UI, this, new EmployeeAddEvent()));
        editEmployeeButton.addClickListener(clickEvent ->
                eventBus.publish(EventScope.UI, this, new EmployeeEditEvent(new Employee())));
        deleteEmployeeButton.addClickListener(clickEvent -> showConfirmationDialog());
    }

    private void constructLayout() {
        MVerticalLayout homeContent = new MVerticalLayout(
                viewNameLabel,
                controlButtonsLayout,
                employeesGrid
        ).withFullHeight().withFullWidth().withMargin(true).expand(employeesGrid);
        addComponent(homeContent);
    }

    @Override
    public void setEmployeesList(Iterable<Employee> employees) {
        employeeContainer.addAll((Collection<? extends Employee>) employees);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    private void showConfirmationDialog() {
        // todo confirmation dialog
        eventBus.publish(EventScope.UI, this, new EmployeeDeleteEvent(new Employee()));
    }
}
