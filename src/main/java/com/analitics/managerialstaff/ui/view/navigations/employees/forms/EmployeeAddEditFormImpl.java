package com.analitics.managerialstaff.ui.view.navigations.employees.forms;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.forms.EntityFormImpl;
import com.analitics.managerialstaff.ui.components.events.EmployeeSaveEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinComponent
public class EmployeeAddEditFormImpl
        extends EntityFormImpl<Employee>
        implements EmployeeAddEditForm, AbstractForm.SavedHandler<Employee>, AbstractForm.ResetHandler<Employee> {

    private MTextField surname;
    private MTextField forename;
    private MTextField age;
    private MTextField position;
    private EnumSelect gender;
    private EnumSelect grade;
    private MTextField experience;

    @Autowired
    public EmployeeAddEditFormImpl(EventBus.UIEventBus eventBus) {
        super(eventBus);
    }

    @PostConstruct
    private void init() {
        initTextFields();
        setSavedHandler(this);
        setResetHandler(this);
    }

    private void initTextFields() {
        surname = new MTextField("Имя")
                .withInputPrompt("Имя")
                .withFullWidth();
        forename = new MTextField("Фамилия")
                .withInputPrompt("Фамилия")
                .withFullWidth();
        age = new MTextField("Возраст")
                .withInputPrompt("Возраст")
                .withFullWidth();
        position = new MTextField("Должность")
                .withInputPrompt("Должность")
                .withFullWidth();
        gender = new EnumSelect("Пол")
                .withFullWidth();
        grade = new EnumSelect("Грэйд")
                .withFullWidth();
        experience = new MTextField("Стаж")
                .withInputPrompt("Стаж")
                .withFullWidth();
    }

    @Override
    protected Component createContent() {
        MFormLayout formLayout = new MFormLayout(
                surname,
                forename,
                age,
                position,
                gender,
                grade,
                experience
        ).withMargin(true).withWidth("400px");
        MVerticalLayout result = new MVerticalLayout(
                formLayout,
                getToolbar()
        ).withMargin(false);
        return result;
    }

    @Override
    public void setFormEntity(Employee employee) {
        setEntity(employee);
    }

    @Override
    public void openInModalWindow() {
        openInModalPopup();
    }

    @Override
    public Window openInModalPopup() {
        Window popup = super.openInModalPopup();
        popup.setCaption("Создние/Редактирование сотрудника");
        return popup;
    }

    @Override
    public void onReset(Employee employee) {
        getPopup().close();
    }

    @Override
    public void onSave(Employee employee) {
        if (isValid()) {
            eventBus.publish(EventScope.UI, this, new EmployeeSaveEvent(employee));
            getPopup().close();
        }
    }
}
