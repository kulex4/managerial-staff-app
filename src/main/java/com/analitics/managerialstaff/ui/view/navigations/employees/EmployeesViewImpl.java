package com.analitics.managerialstaff.ui.view.navigations.employees;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEducationEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeEditEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
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

    private MHorizontalLayout controlButtonsLayout;
    private Button addEmployeeButton;
    private Button deleteEmployeeButton;
    private Button editEmployeeButton;
    private Button addEducationButton;
    private MTable<Employee> employeesTable;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        constructLayout();
    }

    private void initComponents() {
        initButtons();
        initTable();
    }

    private void initButtons() {
        addEmployeeButton = new MButton("Добавить", this::addEmployee).withIcon(FontAwesome.PLUS);
        editEmployeeButton = new MButton("Изменить", this::editEmployee).withIcon(FontAwesome.PENCIL);
        deleteEmployeeButton = new MButton("Удалить", this::deleteEmployee).withIcon(FontAwesome.TRASH_O);
        addEducationButton = new MButton("Образование сотрудника", this::educationEvent).withIcon(FontAwesome.BOOK);

        MHorizontalLayout spacing = new MHorizontalLayout().withFullWidth().withMargin(false);
        controlButtonsLayout = new MHorizontalLayout(
                addEmployeeButton,
                editEmployeeButton,
                deleteEmployeeButton,
                spacing,
                addEducationButton
        ).withMargin(false).expand(spacing);
    }

    private void initTable() {
        employeesTable = new MTable<>(Employee.class);
        employeesTable.withCaption("Список всех сотрудников");
        employeesTable.addMValueChangeListener(this::tableElementSelection);
        employeesTable.setImmediate(true);
        employeesTable.setWidth(100, Unit.PERCENTAGE);

        setupTableColumns();
    }

    private void setupTableColumns() {
        employeesTable.withProperties(
                Employee.SURNAME,
                Employee.FORENAME,
                Employee.GENDER,
                Employee.AGE,
                Employee.GRADE,
                Employee.EXPERIENCE,
                Employee.DEPARTMENT,
                Employee.POSITION
        );
        employeesTable.withColumnHeaders(
                "Фамилия",
                "Имя",
                "Пол",
                "Возраст",
                "Грейд",
                "Стаж",
                "Отдел",
                "Должность"
        );
    }

    private void constructLayout() {
        MVerticalLayout homeContent = new MVerticalLayout(
                controlButtonsLayout,
                employeesTable
        ).withFullHeight().withFullWidth().withMargin(true).expand(employeesTable);
        addComponent(homeContent);
    }

    @Override
    public void setEmployeesList(Iterable<Employee> employees) {
        employeesTable.removeAllItems();
        employeesTable.addBeans((Collection<Employee>) employees);
    }

    @Override
    public void emptyEmployeeNotification() {
        notificationManager.showNotification("Выберете сотрудника из таблицы", MyTheme.NOTIFICATION_WARNING);
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
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        editEmployeeButton.setEnabled(false);
        deleteEmployeeButton.setEnabled(false);
        addEducationButton.setEnabled(false);
    }

    private void addEmployee(Button.ClickEvent event) {
        eventBus.publish(EventScope.UI, this, new EmployeeAddEvent());
    }

    private void editEmployee(Button.ClickEvent event) {
        Employee selectedEmployee = employeesTable.getValue();
        if (selectedEmployee != null && employeesTable.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeEditEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void deleteEmployee(Button.ClickEvent event) {
        // todo confirmation dialog
        Employee selectedEmployee = employeesTable.getValue();
        if (selectedEmployee != null && employeesTable.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeDeleteEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void educationEvent(Button.ClickEvent event) {
        Employee selectedEmployee = employeesTable.getValue();
        if (selectedEmployee != null && employeesTable.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeAddEducationEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void tableElementSelection(MValueChangeEvent event) {
        editEmployeeButton.setEnabled(event.getValue() != null);
        deleteEmployeeButton.setEnabled(event.getValue() != null);
        addEducationButton.setEnabled(event.getValue() != null);
    }
}
