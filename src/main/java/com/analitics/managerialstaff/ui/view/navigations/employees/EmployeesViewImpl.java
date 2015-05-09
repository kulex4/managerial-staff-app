package com.analitics.managerialstaff.ui.view.navigations.employees;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeEditEvent;
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
    private NotificationManager notificationManager;

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

        initButtons();
        initGrid();
        setupGridColumns();
    }

    private void initButtons() {
        addEmployeeButton = new MButton("Добавить").withIcon(FontAwesome.PLUS);
        editEmployeeButton = new MButton("Изменить").withIcon(FontAwesome.PENCIL);
        deleteEmployeeButton = new MButton("Удалить").withIcon(FontAwesome.TRASH_O);

        controlButtonsLayout = new MHorizontalLayout(
                addEmployeeButton,
                editEmployeeButton,
                deleteEmployeeButton
        ).withMargin(false);
    }

    private void initGrid() {
        employeeContainer = new BeanItemContainer<>(Employee.class);
        employeesGrid = new Grid(employeeContainer);
        employeesGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        employeesGrid.setImmediate(true);
        employeesGrid.setWidth("99%");
        employeesGrid.setColumnOrder(
                Employee.SURNAME,
                Employee.FORENAME,
                Employee.GENDER,
                Employee.AGE,
                Employee.EXPERIENCE,
                Employee.POSITION
        );
        employeesGrid.removeColumn("id");
        employeesGrid.removeColumn(Employee.GRADE);
    }

    private void setupGridColumns() {
        Grid.Column surnameColumn = employeesGrid.getColumn(Employee.SURNAME);
        surnameColumn.setHeaderCaption("Имя");
        Grid.Column forenameColumn = employeesGrid.getColumn(Employee.FORENAME);
        forenameColumn.setHeaderCaption("Фамилия");
        Grid.Column genderColumn = employeesGrid.getColumn(Employee.GENDER);
        genderColumn.setHeaderCaption("Пол");
        Grid.Column ageColumn = employeesGrid.getColumn(Employee.AGE);
        ageColumn.setHeaderCaption("Возраст");
        Grid.Column experienceColumn = employeesGrid.getColumn(Employee.EXPERIENCE);
        experienceColumn.setHeaderCaption("Стаж");
        Grid.Column positionColumn = employeesGrid.getColumn(Employee.POSITION);
        positionColumn.setHeaderCaption("Должность");
    }

    private void initListeners() {
        addEmployeeButton.addClickListener(clickEvent -> eventBus.publish(EventScope.UI, this, new EmployeeAddEvent()));
        editEmployeeButton.addClickListener(clickEvent -> constructEditEvent());
        deleteEmployeeButton.addClickListener(clickEvent -> constructDeleteEvent());
        employeesGrid.addSelectionListener(selectionEvent -> {
            editEmployeeButton.setEnabled(true);
            deleteEmployeeButton.setEnabled(true);
        });
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
        employeeContainer.removeAllItems();
        employeeContainer.addAll((Collection<? extends Employee>) employees);
    }

    @Override
    public void emptyEmployeeNotification() {
        notificationManager.showNotification("Выберете сотрудника из таблицы", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void saveEmployeeSuccessNotification() {
        notificationManager.showNotification("Новый сотрудник успешно создан", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void editEmployeeSuccessNotification() {
        notificationManager.showNotification("Данные сотрудника успешно изменены", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void deleteEmployeeSuccessNotification() {
        notificationManager.showNotification("Сотрудник успешно удален", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    private void constructEditEvent() {
        Employee selectedEmployee = (Employee) employeesGrid.getSelectedRow();
        if (selectedEmployee != null && employeeContainer.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeEditEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void constructDeleteEvent() {
        // todo confirmation dialog
        Employee selectedEmployee = (Employee) employeesGrid.getSelectedRow();
        if (selectedEmployee != null && employeeContainer.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeDeleteEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }
}
