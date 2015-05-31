package com.analitics.managerialstaff.ui.view.navigations.employees;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEducationEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeAddEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeEditEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.SelectionEvent;
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

    private MHorizontalLayout controlButtonsLayout;
    private Button addEmployeeButton;
    private Button deleteEmployeeButton;
    private Button editEmployeeButton;
    private Button addEducationButton;
    private Grid employeesGrid;
    private BeanItemContainer<Employee> employeeContainer;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        constructLayout();
    }

    private void initComponents() {
        initButtons();
        initGrid();
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

    private void initGrid() {
        employeeContainer = new BeanItemContainer<>(Employee.class);
        employeesGrid = new Grid("Список всех сотрудников", employeeContainer);
        employeesGrid.addSelectionListener(this::gridElementSelection);
        employeesGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        employeesGrid.setImmediate(true);
        employeesGrid.setWidth("99%");
        employeesGrid.setColumnOrder(
                Employee.SURNAME,
                Employee.FORENAME,
                Employee.GENDER,
                Employee.AGE,
                Employee.GRADE,
                Employee.EXPERIENCE,
                Employee.DEPARTMENT,
                Employee.POSITION
        );
        removeUnusedColumns();
        setColumnsCaption();
    }

    private void removeUnusedColumns() {
        employeesGrid.removeColumn("id");
        employeesGrid.removeColumn(Employee.CERTIFICATIONS);
        employeesGrid.removeColumn(Employee.EDUCATIONS);
        employeesGrid.removeColumn(Employee.TRAININGS);
    }

    private void setColumnsCaption() {
        Grid.Column surnameColumn = employeesGrid.getColumn(Employee.SURNAME);
        surnameColumn.setHeaderCaption("Имя");
        Grid.Column forenameColumn = employeesGrid.getColumn(Employee.FORENAME);
        forenameColumn.setHeaderCaption("Фамилия");
        Grid.Column genderColumn = employeesGrid.getColumn(Employee.GENDER);
        genderColumn.setHeaderCaption("Пол");
        Grid.Column ageColumn = employeesGrid.getColumn(Employee.AGE);
        ageColumn.setHeaderCaption("Возраст");
        Grid.Column gradeColumn = employeesGrid.getColumn(Employee.GRADE);
        gradeColumn.setHeaderCaption("Грейд");
        Grid.Column experienceColumn = employeesGrid.getColumn(Employee.EXPERIENCE);
        experienceColumn.setHeaderCaption("Стаж");
        Grid.Column departmentColumn = employeesGrid.getColumn(Employee.DEPARTMENT);
        departmentColumn.setHeaderCaption("Отдел");
        Grid.Column positionColumn = employeesGrid.getColumn(Employee.POSITION);
        positionColumn.setHeaderCaption("Должность");
    }

    private void constructLayout() {
        MVerticalLayout homeContent = new MVerticalLayout(
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
        Employee selectedEmployee = (Employee) employeesGrid.getSelectedRow();
        if (selectedEmployee != null && employeeContainer.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeEditEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void deleteEmployee(Button.ClickEvent event) {
        // todo confirmation dialog
        Employee selectedEmployee = (Employee) employeesGrid.getSelectedRow();
        if (selectedEmployee != null && employeeContainer.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeDeleteEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void educationEvent(Button.ClickEvent event) {
        Employee selectedEmployee = (Employee) employeesGrid.getSelectedRow();
        if (selectedEmployee != null && employeeContainer.getItemIds().contains(selectedEmployee)) {
            eventBus.publish(EventScope.UI, this, new EmployeeAddEducationEvent(selectedEmployee));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void gridElementSelection(SelectionEvent event) {
        editEmployeeButton.setEnabled(!event.getSelected().isEmpty());
        deleteEmployeeButton.setEnabled(!event.getSelected().isEmpty());
        addEducationButton.setEnabled(!event.getSelected().isEmpty());
    }
}
