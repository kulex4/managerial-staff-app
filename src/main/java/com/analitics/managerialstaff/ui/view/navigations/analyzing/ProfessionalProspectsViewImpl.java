package com.analitics.managerialstaff.ui.view.navigations.analyzing;

import com.analitics.managerialstaff.backend.model.Employee;
import com.analitics.managerialstaff.backend.model.enums.Department;
import com.analitics.managerialstaff.backend.model.enums.Grade;
import com.analitics.managerialstaff.ui.components.events.EmployeesSelectEvent;
import com.analitics.managerialstaff.ui.theme.MyTheme;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.navigator.annotation.VaadinView;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * @author by nikolai.pashkevich
 */
@VaadinUIScope
@VaadinView(name = ProfessionalProspectsView.NAME)
public class ProfessionalProspectsViewImpl extends VerticalLayout implements ProfessionalProspectsView {

    @Autowired
    private EventBus.UIEventBus eventBus;

    public Employee emptyEmployee;
    public static final String NOT_SELECTED = "не выбран";
    public static final String EMPTY_LABEL = "---";
    public static final String SURNAME_LABEL = "Фамилия";
    public static final String FORENAME_LABEL = "Имя";

    public static final String RESULT_TEXT_LESS_THAN_THREE = "<b>0 < K < 3</b> " +
            "Сотрудник имеет не высокий результат профессиональной перспективности " +
            "в силу возраста и небольшого опыта работы. Его нельзя повысить в должности в ближайшее время.";
    public static final String RESULT_TEXT_LESS_THAN_FOUR = "<b>3 <= K < 4.5</b> " +
            "Сотрудник имеет высокий результат профессиональной перспективности " +
            "и может быть рассмотрен вопрос о его повышении в должности в ближайший год.";
    public static final String RESULT_TEXT_MORE_THAN_FOUR = "<b>K > 4.5</b> " +
            "Сотрудник имеет очень высокий результат профессиональной перспективности " +
            "и может быть повышен в должности в ближайшее время.";

    private ComboBox gradeSelect;
    private ComboBox departmentSelect;
    private ComboBox employeeSelect;
    private BeanItemContainer<Employee> employeeContainer;

    private Label surname;
    private Label forename;
    private Label educationLevelLabel;
    private Label educationLevel;
    private Label experienceLabel;
    private Label experience;
    private Label ageLabel;
    private Label age;
    private Label resultLabel;
    private Label result;
    private Label conclusion;

    @PostConstruct
    private void init() {
        setSizeFull();
        setMargin(new MarginInfo(true, false, false, false));
        generateEmptyEmployee();
        initLabels();
        initComponents();
        constructLayout();
    }

    private void generateEmptyEmployee() {
        emptyEmployee = new Employee();
        emptyEmployee.setSurname(NOT_SELECTED);
        emptyEmployee.setForename("");
    }

    private void initComponents() {
        gradeSelect = new ComboBox("Грейд");
        gradeSelect.addValueChangeListener(this::selectedGrade);
        gradeSelect.setNullSelectionAllowed(true);
        gradeSelect.setNullSelectionItemId(NOT_SELECTED);
        gradeSelect.setWidth(150, Unit.PIXELS);
        gradeSelect.addItems(
                NOT_SELECTED,
                Grade.SPECIALIST,
                Grade.MANAGER
        );
        gradeSelect.setValue(null);

        departmentSelect = new ComboBox("Отдел");
        departmentSelect.addValueChangeListener(this::selectedDepartment);
        departmentSelect.setNullSelectionAllowed(true);
        departmentSelect.setNullSelectionItemId(NOT_SELECTED);
        departmentSelect.setWidth(500, Unit.PIXELS);
        departmentSelect.addItems(
                NOT_SELECTED,
                Department.CHIEF_ACCOUNTANT,
                Department.FINANCIAL_ACCOUNTING_AND_REPORTING,
                Department.COST_ACCOUNTING,
                Department.TAX_REPORTING,
                Department.CONSOLIDATED_FINANCIAL_STATEMENTS
        );
        departmentSelect.setValue(null);

        employeeContainer = new BeanItemContainer<>(Employee.class);
        employeeSelect = new ComboBox("Специалист", employeeContainer);
        employeeSelect.addValueChangeListener(this::selectedEmployee);
        employeeSelect.setItemCaptionMode(AbstractSelect.ItemCaptionMode.EXPLICIT);
        employeeSelect.setNullSelectionAllowed(true);
        employeeSelect.setNullSelectionItemId(emptyEmployee);
        employeeSelect.setNullSelectionItemId(NOT_SELECTED);
        employeeSelect.setWidth(300, Unit.PIXELS);

        employeeContainer.addItem(emptyEmployee);
        employeeSelect.setValue(emptyEmployee);
        generateEmployeesCaption();
    }

    private void initLabels() {
        surname = new Label(SURNAME_LABEL);
        surname.setStyleName(MyTheme.LABEL_H1);
        surname.addStyleName(MyTheme.LABEL_COLORED);

        forename = new Label(FORENAME_LABEL);
        forename.setStyleName(MyTheme.LABEL_H1);
        forename.addStyleName(MyTheme.LABEL_COLORED);

        educationLevelLabel = new Label("Оценка уровня образования:");
        educationLevel = new Label(EMPTY_LABEL);
        educationLevel.setStyleName(MyTheme.LABEL_COLORED);

        experienceLabel = new Label("Стаж работы по специальности:");
        experience = new Label(EMPTY_LABEL);
        experience.setStyleName(MyTheme.LABEL_COLORED);

        ageLabel = new Label("Возраст:");
        age = new Label(EMPTY_LABEL);
        age.setStyleName(MyTheme.LABEL_COLORED);

        resultLabel = new Label("Результат (K):");
        result = new Label(EMPTY_LABEL);
        result.setStyleName(MyTheme.LABEL_COLORED);

        conclusion = new Label(EMPTY_LABEL);
        conclusion.setContentMode(ContentMode.HTML);
    }

    private void constructLayout() {
        MVerticalLayout searchLayout = new MVerticalLayout(
                new MHorizontalLayout(
                        gradeSelect,
                        departmentSelect,
                        employeeSelect
                ).withMargin(false)
        ).withStyleName(MyTheme.LAYOUT_CARD);
        addComponent(searchLayout);

        addComponent(new MHorizontalLayout().withMargin(false).withHeight("37px"));

        MVerticalLayout resultLayout = new MVerticalLayout(
                new MVerticalLayout(
                        new MHorizontalLayout(
                                surname,
                                forename
                        ).withMargin(false),
                        new MHorizontalLayout(
                                new MVerticalLayout(
                                        educationLevelLabel,
                                        experienceLabel,
                                        ageLabel,
                                        new MHorizontalLayout().withMargin(false).withHeight("20px"),
                                        resultLabel
                                ).withMargin(false),
                                new MVerticalLayout().withWidth("100px"),
                                new MVerticalLayout(
                                        educationLevel,
                                        experience,
                                        age,
                                        new MHorizontalLayout().withMargin(false).withHeight("20px"),
                                        result
                                ).withMargin(false)
                        ),
                        new MHorizontalLayout().withMargin(false).withHeight("20px"),
                        conclusion
                ).withMargin(false).alignAll(Alignment.TOP_LEFT)
        ).withStyleName(MyTheme.LAYOUT_CARD).withFullWidth();
        addComponent(resultLayout);
        setExpandRatio(resultLayout, 1);
    }

    private void selectedGrade(Property.ValueChangeEvent event) {
        Department department = (Department) departmentSelect.getValue();
        Grade grade = (Grade) gradeSelect.getValue();
        eventBus.publish(EventScope.UI, this, new EmployeesSelectEvent(department, grade));
    }

    private void selectedDepartment(Property.ValueChangeEvent event) {
        Department department = (Department) departmentSelect.getValue();
        Grade grade = (Grade) gradeSelect.getValue();
        eventBus.publish(EventScope.UI, this, new EmployeesSelectEvent(department, grade));
    }

    private void selectedEmployee(Property.ValueChangeEvent event) {
        Employee selectedEmployee = (Employee) event.getProperty().getValue();
        if (!emptyEmployee.equals(selectedEmployee)) {
            float resultIndex = calculateResultForEmployee(selectedEmployee);
            surname.setValue(selectedEmployee.getSurname());
            forename.setValue(selectedEmployee.getForename());
            educationLevel.setValue(String.valueOf(calculateEducationLevel(selectedEmployee)));
            experience.setValue(String.valueOf(selectedEmployee.getExperience()));
            age.setValue(String.valueOf(selectedEmployee.getAge()));
            result.setValue(String.valueOf(resultIndex));
            conclusion.setValue(resultIndex > 4.5F ? RESULT_TEXT_MORE_THAN_FOUR :
                    resultIndex > 3F ? RESULT_TEXT_LESS_THAN_FOUR :
                            RESULT_TEXT_LESS_THAN_THREE);
            conclusion.setStyleName(resultIndex > 3F ? MyTheme.LABEL_SUCCESS : MyTheme.LABEL_FAILURE);
        } else {
            surname.setValue(SURNAME_LABEL);
            forename.setValue(FORENAME_LABEL);
            educationLevel.setValue(EMPTY_LABEL);
            experience.setValue(EMPTY_LABEL);
            age.setValue(EMPTY_LABEL);
            result.setValue(EMPTY_LABEL);
            conclusion.setStyleName(MyTheme.LABEL_FAILURE);
            conclusion.setValue("Результат (K) не известен");
        }
    }

    private float calculateResultForEmployee(Employee employee) {
        return calculateEducationLevel(employee)
                * (1 + (employee.getExperience() / 4) + (employee.getAge() / 18));
    }

    private float calculateEducationLevel(Employee employee) {
        return 1F;
    }

    @Override
    public void setEmployees(Iterable<Employee> employees) {
        employeeContainer.removeAllItems();
        employeeContainer.addItem(emptyEmployee);
        employeeContainer.addAll((Collection<? extends Employee>) employees);
        generateEmployeesCaption();
        if (!((Collection<? extends Employee>) employees).isEmpty()) {
            employeeSelect.select(employees.iterator().next());
        } else {
            employeeSelect.select(emptyEmployee);
        }
    }

    private void generateEmployeesCaption() {
        for (Object o : employeeSelect.getItemIds()) {
            Employee employee = (Employee) o;
            employeeSelect.setItemCaption(employee, employee.getSurname() + " " + employee.getForename());
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
