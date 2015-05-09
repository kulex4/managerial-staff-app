package com.analitics.managerialstaff.ui.view.navigations.managers.forms;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.analitics.managerialstaff.ui.common.forms.EntityFormImpl;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeSaveEvent;
import com.analitics.managerialstaff.ui.components.events.managers.ManagerSaveEvent;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
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
public class ManagerAddEditFormImpl
        extends EntityFormImpl<Employee>
        implements ManagerAddEditForm, AbstractForm.SavedHandler<Employee>, AbstractForm.ResetHandler<Employee> {

    public static final int STRING_MIN_VALUE = 2;
    public static final int STRING_MAX_VALUE = 30;
    public static final int AGE_MIN_VALUE = 18;
    public static final int AGE_MAX_VALUE = 100;
    public static final int EXPERIENCE_MIN_VALUE = 0;
    public static final int EXPERIENCE_MAX_VALUE = 50;

    private MTextField surname;
    private MTextField forename;
    private MTextField age;
    private MTextField position;
    private EnumSelect gender;
    private MTextField experience;

    @Autowired
    public ManagerAddEditFormImpl(EventBus.UIEventBus eventBus) {
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
                .withValidator(new StringLengthValidator(
                        "Имя не может быть пустым",
                        STRING_MIN_VALUE,
                        STRING_MAX_VALUE,
                        false
                ))
                .withFullWidth();
        forename = new MTextField("Фамилия")
                .withInputPrompt("Фамилия")
                .withValidator(new StringLengthValidator(
                        "Фамилия не может быть пустой",
                        STRING_MIN_VALUE,
                        STRING_MAX_VALUE,
                        false
                ))
                .withFullWidth();
        age = new MTextField("Возраст")
                .withInputPrompt("Возраст")
                .withValidator(new IntegerRangeValidator(
                        String.format("Возраст должен быть от %d до %d",
                                AGE_MIN_VALUE,
                                AGE_MAX_VALUE
                        ),
                        AGE_MIN_VALUE,
                        AGE_MAX_VALUE
                ))
                .withFullWidth();
        position = new MTextField("Должность")
                .withInputPrompt("Должность")
                .withValidator(new StringLengthValidator(
                        "Должность не может быть пустой",
                        STRING_MIN_VALUE,
                        STRING_MAX_VALUE,
                        false
                ))
                .withFullWidth();
        gender = new EnumSelect("Пол")
                .withValidator(new NullValidator("Выберете пол", false))
                .withFullWidth();
        experience = new MTextField("Стаж")
                .withInputPrompt("Стаж")
                .withValidator(new IntegerRangeValidator(
                        String.format(
                                "Стаж должен быть от %d до %d",
                                EXPERIENCE_MIN_VALUE,
                                EXPERIENCE_MAX_VALUE
                        ),
                        EXPERIENCE_MIN_VALUE,
                        EXPERIENCE_MAX_VALUE
                ))
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
                experience
        ).withMargin(true).withWidth("400px");
        return new MVerticalLayout(
                formLayout,
                getToolbar()
        ).withMargin(false);
    }

    @Override
    public void setFormEntity(Employee manager) {
        setEntity(manager);
    }

    @Override
    public void openInModalWindow() {
        openInModalPopup();
    }

    @Override
    public Window openInModalPopup() {
        Window popup = super.openInModalPopup();
        popup.setCaption("Создние/Редактирование менеджера");
        return popup;
    }

    @Override
    public void onReset(Employee manager) {
        getPopup().close();
    }

    @Override
    public void onSave(Employee manager) {
        manager.setGrade(Grade.MANAGER);
        if (isValid()) {
            eventBus.publish(EventScope.UI, this, new ManagerSaveEvent(manager, manager.getId() == null));
            getPopup().close();
        }
    }
}
