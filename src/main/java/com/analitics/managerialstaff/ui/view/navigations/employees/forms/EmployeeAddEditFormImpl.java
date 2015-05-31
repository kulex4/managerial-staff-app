package com.analitics.managerialstaff.ui.view.navigations.employees.forms;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.analitics.managerialstaff.ui.common.forms.EntityFormImpl;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeSaveEvent;
import com.analitics.managerialstaff.ui.view.navigations.employees.validators.EmployeeForenameValidator;
import com.analitics.managerialstaff.ui.view.navigations.employees.validators.EmployeeSurnameValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
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
    private EnumSelect department;
    private MTextField experience;

    @Autowired
    public EmployeeAddEditFormImpl(EventBus.UIEventBus eventBus) {
        super(eventBus);
    }

    @PostConstruct
    private void init() {
        initButtons();
        initTextFields();
        setSavedHandler(this);
        setResetHandler(this);
    }

    private void initButtons() {
        getSaveButton().setCaption("Сохранить");
        getResetButton().setCaption("Отмена");
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
                .withSelectType(ComboBox.class)
                .withNullSelection(false)
                .withValidator(new NullValidator("Выберете пол", false))
                .withFullWidth();
        department = new EnumSelect("Отдел")
                .withSelectType(ComboBox.class)
                .withNullSelection(false)
                .withValidator(new NullValidator("Выберете отдел", false))
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
                department,
                experience
        ).withMargin(true).withWidth("500px");
        return new MVerticalLayout(
                formLayout,
                getToolbar()
        ).withMargin(false);
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
        employee.setGrade(Grade.SPECIALIST);
        if (isValid()) {
            eventBus.publish(EventScope.UI, this, new EmployeeSaveEvent(employee, employee.getId() == null));
            getPopup().close();
        }
    }
}
