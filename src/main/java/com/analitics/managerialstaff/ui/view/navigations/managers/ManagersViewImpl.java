package com.analitics.managerialstaff.ui.view.navigations.managers;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.NotificationManager;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerAddEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerEditEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
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
import java.util.Optional;
import java.util.Set;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = ManagersView.NAME)
public class ManagersViewImpl extends VerticalLayout implements ManagersView {

    @Autowired
    private NotificationManager notificationManager;

    @Autowired
    private EventBus.UIEventBus eventBus;

    private MHorizontalLayout controlButtonsLayout;
    private Button addManagerButton;
    private Button deleteManagerButton;
    private Button editManagerButton;
    private Button addEducationButton;
    private Grid managersGrid;
    private BeanItemContainer<Employee> managersContainer;

    @PostConstruct
    private void init() {
        setSizeFull();

        initComponents();
        initListeners();
        constructLayout();
    }

    private void initComponents() {
        initButtons();
        initGrid();
    }

    private void initButtons() {
        addManagerButton = new MButton("Добавить").withIcon(FontAwesome.PLUS);
        editManagerButton = new MButton("Изменить").withIcon(FontAwesome.PENCIL);
        deleteManagerButton = new MButton("Удалить").withIcon(FontAwesome.TRASH_O);
        addEducationButton = new MButton("Добавить образование сотруднику").withIcon(FontAwesome.BOOK);

        MHorizontalLayout spacing = new MHorizontalLayout().withFullWidth().withMargin(false);
        controlButtonsLayout = new MHorizontalLayout(
                addManagerButton,
                editManagerButton,
                deleteManagerButton,
                spacing,
                addEducationButton
        ).withMargin(false).expand(spacing);
    }

    private void initGrid() {
        managersContainer = new BeanItemContainer<>(Employee.class);
        managersGrid = new Grid("Список всех менеджеров", managersContainer);
        managersGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        managersGrid.setImmediate(true);
        managersGrid.setWidth("99%");
        managersGrid.setColumnOrder(
                Employee.SURNAME,
                Employee.FORENAME,
                Employee.GENDER,
                Employee.AGE,
                Employee.EXPERIENCE,
                Employee.DEPARTMENT,
                Employee.POSITION
        );
        removeUnusedColumns();
        setColumnsCaption();
    }

    private void removeUnusedColumns() {
        managersGrid.removeColumn("id");
        managersGrid.removeColumn(Employee.GRADE);
        managersGrid.removeColumn(Employee.CERTIFICATIONS);
        managersGrid.removeColumn(Employee.EDUCATIONS);
        managersGrid.removeColumn(Employee.TRAININGS);
    }

    private void setColumnsCaption() {
        Grid.Column surnameColumn = managersGrid.getColumn(Employee.SURNAME);
        surnameColumn.setHeaderCaption("Имя");
        Grid.Column forenameColumn = managersGrid.getColumn(Employee.FORENAME);
        forenameColumn.setHeaderCaption("Фамилия");
        Grid.Column genderColumn = managersGrid.getColumn(Employee.GENDER);
        genderColumn.setHeaderCaption("Пол");
        Grid.Column ageColumn = managersGrid.getColumn(Employee.AGE);
        ageColumn.setHeaderCaption("Возраст");
        Grid.Column experienceColumn = managersGrid.getColumn(Employee.EXPERIENCE);
        experienceColumn.setHeaderCaption("Стаж");
        Grid.Column departmentColumn = managersGrid.getColumn(Employee.DEPARTMENT);
        departmentColumn.setHeaderCaption("Отдел");
        Grid.Column positionColumn = managersGrid.getColumn(Employee.POSITION);
        positionColumn.setHeaderCaption("Должность");
    }

    private void initListeners() {
        addManagerButton.addClickListener(clickEvent -> eventBus.publish(EventScope.UI, this, new ManagerAddEvent()));
        editManagerButton.addClickListener(clickEvent -> constructEditEvent());
        deleteManagerButton.addClickListener(clickEvent -> constructDeleteEvent());
        managersGrid.addSelectionListener(selectionEvent -> {
            editManagerButton.setEnabled(true);
            deleteManagerButton.setEnabled(true);
            addEducationButton.setEnabled(true);
        });
    }

    private void constructLayout() {
        MVerticalLayout homeContent = new MVerticalLayout(
                controlButtonsLayout,
                managersGrid
        ).withFullHeight().withFullWidth().withMargin(true).expand(managersGrid);
        addComponent(homeContent);
    }

    @Override
    public void setEmployeesList(Iterable<Employee> employees) {
        managersContainer.removeAllItems();
        managersContainer.addAll((Collection<? extends Employee>) employees);
    }

    @Override
    public void emptyEmployeeNotification() {
        notificationManager.showNotification("Выберете менеджера из таблицы", MyTheme.NOTIFICATION_WARNING);
    }

    @Override
    public void saveEmployeeSuccessNotification() {
        notificationManager.showNotification("Новый менеджер успешно создан", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void editEmployeeSuccessNotification() {
        notificationManager.showNotification("Данные менеджера успешно изменены", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void deleteEmployeeSuccessNotification() {
        notificationManager.showNotification("Менеджер успешно удален", MyTheme.NOTIFICATION_SUCCESS);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        editManagerButton.setEnabled(false);
        deleteManagerButton.setEnabled(false);
        addEducationButton.setEnabled(false);
    }

    private void constructEditEvent() {
        Employee selectedManager = (Employee) managersGrid.getSelectedRow();
        if (selectedManager != null && managersContainer.getItemIds().contains(selectedManager)) {
            eventBus.publish(EventScope.UI, this, new ManagerEditEvent(selectedManager));
        } else {
            emptyEmployeeNotification();
        }
    }

    private void constructDeleteEvent() {
        // todo confirmation dialog
        Employee selectedManager = (Employee) managersGrid.getSelectedRow();
        if (selectedManager != null && managersContainer.getItemIds().contains(selectedManager)) {
            eventBus.publish(EventScope.UI, this, new ManagerDeleteEvent(selectedManager));
        } else {
            emptyEmployeeNotification();
        }
    }
}
