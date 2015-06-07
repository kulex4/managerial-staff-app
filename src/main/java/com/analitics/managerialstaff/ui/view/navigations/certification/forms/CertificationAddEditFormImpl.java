package com.analitics.managerialstaff.ui.view.navigations.certification.forms;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.forms.EntityFormImpl;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationSaveEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.ui.*;
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
import java.util.Collection;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinComponent
public class CertificationAddEditFormImpl
        extends EntityFormImpl<Certification>
        implements CertificationAddEditForm, AbstractForm.SavedHandler<Certification>, AbstractForm.ResetHandler<Certification> {

    private ComboBox employee;
    private EnumSelect year;
    private EnumSelect quarter;
    private TextField responsibility;
    private TextField competence;
    private TextField communicability;
    private TextField testResult;

    private BeanItemContainer<Employee> employeeBeanItemContainer;

    @Autowired
    public CertificationAddEditFormImpl(EventBus.UIEventBus eventBus) {
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
        employeeBeanItemContainer = new BeanItemContainer<>(Employee.class);
        employee = new ComboBox("Сотрудник", employeeBeanItemContainer);
        employee.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        employee.setNullSelectionAllowed(false);
        employee.setImmediate(true);
        employee.setWidth(100, Unit.PERCENTAGE);

        year = new EnumSelect("Год аттестации")
                .withSelectType(ComboBox.class)
                .withNullSelection(false)
                .withValidator(new NullValidator("Выберете год аттестации", false))
                .withFullWidth();
        quarter = new EnumSelect("Квартал аттестации")
                .withSelectType(ComboBox.class)
                .withNullSelection(false)
                .withValidator(new NullValidator("Выберете квартал аттестации", false))
                .withFullWidth();
        responsibility = new MTextField("Ответственность")
                .withInputPrompt("Ответственность")
                .withFullWidth();
        competence = new MTextField("Компетентность")
                .withInputPrompt("Компетентность")
                .withFullWidth();
        communicability = new MTextField("Коммуникабельность")
                .withInputPrompt("Коммуникабельность")
                .withFullWidth();
        testResult = new MTextField("Результаты теста")
                .withInputPrompt("Результаты теста")
                .withFullWidth();
    }

    @Override
    protected Component createContent() {
        MFormLayout formLayout = new MFormLayout(
                employee,
                year,
                quarter,
                responsibility,
                competence,
                communicability,
                testResult
        ).withMargin(true).withWidth("500px");
        return new MVerticalLayout(
                formLayout,
                getToolbar()
        ).withMargin(false);
    }

    @Override
    public void setFormEntity(Certification certification) {
        setEntity(certification);
    }

    @Override
    public void openInModalWindow() {
        openInModalPopup();
    }

    @Override
    public Window openInModalPopup() {
        Window popup = super.openInModalPopup();
        popup.setCaption("Создние/Редактирование аттестации");
        return popup;
    }

    @Override
    public void onReset(Certification entity) {
        getPopup().close();
    }

    @Override
    public void onSave(Certification certification) {
        if (isValid()) {
            eventBus.publish(EventScope.UI, this, new CertificationSaveEvent(certification, certification.getId() == null));
            getPopup().close();
        }
    }

    @Override
    public void setEmployees(Iterable<Employee> employees) {
        employeeBeanItemContainer.removeAllItems();
        employeeBeanItemContainer.addAll((Collection<? extends Employee>) employees);
        generateEmployeesCaption();
    }

    private void generateEmployeesCaption() {
        for (Object o : employee.getItemIds()) {
            Employee e = (Employee) o;
            employee.setItemCaption(e, e.getSurname() + " " + e.getForename());
        }
    }
}
