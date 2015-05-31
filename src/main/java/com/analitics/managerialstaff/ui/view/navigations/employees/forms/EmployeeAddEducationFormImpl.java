package com.analitics.managerialstaff.ui.view.navigations.employees.forms;

import com.analitics.managerialstaff.backend.model.Education;
import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.ui.common.forms.EntityFormImpl;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeDeleteEducationEvent;
import com.analitics.managerialstaff.ui.components.events.employees.EmployeeSaveEducationEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.EnumSelect;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinComponent
public class EmployeeAddEducationFormImpl extends EntityFormImpl<Employee> implements EmployeeAddEducationForm {

    private MTable<Education> educationsTable;

    private Button addEducationButton;
    private Button deleteEducationButton;
    private EducationForm educationForm;

    private Button closeButton;

    @Autowired
    public EmployeeAddEducationFormImpl(EventBus.UIEventBus eventBus) {
        super(eventBus);
        initComponents();
    }

    private void initComponents() {
        educationsTable = new MTable<>(Education.class)
                .withHeight("300px")
                .withWidth("400px")
                .withProperties("university", "speciality")
                .withColumnHeaders("Университет", "Специальность");
        educationsTable.addMValueChangeListener(this::educationSelected);
        addEducationButton = new MButton(FontAwesome.PLUS, this::addNewEducation)
                .withStyleName(MyTheme.BUTTON_ICON_ONLY);
        deleteEducationButton = new MButton(FontAwesome.TRASH_O, this::deleteSelectedEducation)
                .withStyleName(MyTheme.BUTTON_ICON_ONLY);
        deleteEducationButton.setEnabled(false);
        closeButton = new MButton("Закрыть", this::closeWindow);

        educationForm = new EducationForm();
        educationForm.setVisible(false);
        educationForm.setSavedHandler(this::saveEducation);
        educationForm.setResetHandler(this::resetEducation);
    }

    @Override
    protected Component createContent() {
        MHorizontalLayout controlsLayout = new MHorizontalLayout(
                addEducationButton,
                deleteEducationButton
        ).withMargin(false);

        MHorizontalLayout rootLayout = new MHorizontalLayout(
                educationsTable,
                educationForm
        ).withMargin(false);

        return new MVerticalLayout(
                controlsLayout,
                rootLayout,
                getToolbar()
        ).withMargin(true);
    }

    @Override
    public HorizontalLayout getToolbar() {
        MHorizontalLayout result = new MHorizontalLayout().withFullWidth();
        result.addStyleName(MyTheme.WINDOW_BOTTOM_TOOLBAR);
        MHorizontalLayout placeHolder = new MHorizontalLayout();
        result.addComponents(placeHolder, closeButton);
        result.setExpandRatio(placeHolder, 1);
        return result;
    }

    private void addNewEducation(Button.ClickEvent event) {
        deleteEducationButton.setEnabled(false);
        educationsTable.setValue(null);
        Education education = new Education();
        education.setEmployee(getEntity());
        editEducation(education);
    }

    private void deleteSelectedEducation(Button.ClickEvent event) {
        if (educationsTable.getValue() != null) {
            getEntity().getEducations().remove(educationsTable.getValue());
            eventBus.publish(EventScope.UI, this, new EmployeeDeleteEducationEvent(educationsTable.getValue()));
            educationsTable.setBeans(getEntity().getEducations());
            educationsTable.setValue(null);
        }
    }

    @Override
    public void setFormEntity(Employee employee) {
        setEntity(employee);
        educationsTable.setBeans(employee.getEducations());
    }

    @Override
    public void openInModalWindow() {
        openInModalPopup();
    }

    @Override
    public Window openInModalPopup() {
        Window popup = super.openInModalPopup();
        popup.setCaption("Добавление/редактирование образования для сотрудника");
        return popup;
    }

    private void educationSelected(MValueChangeEvent<Education> event) {
        educationForm.setVisible(event.getValue() != null);
        deleteEducationButton.setEnabled(event.getValue() != null);
        editEducation(event.getValue());
    }

    private void resetEducation(Education education) {
        editEducation(educationsTable.getValue());
    }

    private void editEducation(Education education) {
        if (education == null) {
            educationForm.setReadOnly(true);
            deleteEducationButton.setEnabled(false);
        } else {
            deleteEducationButton.setEnabled(true);
            educationForm.setEntity(education);
            educationForm.focusFirst();
        }
    }

    private void saveEducation(Education education) {
        eventBus.publish(EventScope.UI, this, new EmployeeSaveEducationEvent(education));
        educationForm.setVisible(false);
        educationsTable.setValue(null);
        educationsTable.setBeans(getEntity().getEducations());
    }

    private void closeWindow(Button.ClickEvent event) {
        educationForm.setEntity(null);
        getPopup().close();
    }

    private static final class EducationForm extends AbstractForm<Education> {

        private TextField status;
        private EnumSelect university;
        private TextField speciality;
        private DateField dateOfGraduation;

        private EducationForm() {
            status = new MTextField("Статус")
                    .withValidator(new NullValidator("", false))
                    .withFullWidth();
            university = new EnumSelect("Университет")
                    .withValidator(new NullValidator("", false))
                    .withSelectType(ComboBox.class)
                    .withNullSelection(false)
                    .withFullWidth();
            speciality = new MTextField("Специальность")
                    .withValidator(new NullValidator("", false))
                    .withFullWidth();
            dateOfGraduation = new DateField("Год окончания");
            dateOfGraduation.setWidth(100, Unit.PERCENTAGE);
            dateOfGraduation.addValidator(new NullValidator("", false));
        }

        @Override
        protected Component createContent() {
            return new MFormLayout(
                    //getToolbar(),
                    status,
                    university,
                    speciality,
                    dateOfGraduation
            ).withMargin(true).withWidth("400px");
        }
    }
}
