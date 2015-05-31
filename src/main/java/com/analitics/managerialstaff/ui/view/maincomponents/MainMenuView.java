package com.analitics.managerialstaff.ui.view.maincomponents;

import com.analitics.managerialstaff.ui.components.commands.*;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuView extends VerticalLayout {

    @Autowired private HomeMenuCommand homeMenuCommand;
    @Autowired private DepartmentsMenuCommand departmentsMenuCommand;
    @Autowired private CertificationsMenuCommand certificationsMenuCommand;
    @Autowired private EmployeesMenuCommand employeesMenuCommand;
    @Autowired private EducationMenuCommand educationMenuCommand;
    @Autowired private ExperienceMenuCommand experienceMenuCommand;
    @Autowired private GenderMenuCommand genderMenuCommand;
    @Autowired private AverageAgeMenuCommand averageAgeMenuCommand;
    @Autowired private EducationAverageMarkMenuCommand educationAverageMarkMenuCommand;
    @Autowired private TrainingUniformityCoefMenuCommand trainingUniformityCoefMenuCommand;
    @Autowired private ProfessionalProspectsMenuCommand professionalProspectsMenuCommand;

    @PostConstruct
    private void initMainMenu() {

        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Главная", null, homeMenuCommand);
        mainMenuBar.addItem("Департаменты", null, departmentsMenuCommand);
        mainMenuBar.addItem("Аттестация", null, certificationsMenuCommand);
        mainMenuBar.addItem("Сотрудники", null, employeesMenuCommand);

        MenuBar.MenuItem reportItem = mainMenuBar.addItem("Отчетность", null, null);
        reportItem.addItem("Образование сотрудников", null, educationMenuCommand);
        reportItem.addItem("Опыт работы", null, experienceMenuCommand);
        reportItem.addItem("Гендерная принадлежность", null, genderMenuCommand);
        reportItem.addItem("Возраст сотрудников", null, averageAgeMenuCommand);
        reportItem.addItem("Полный отчет о сотрудниках", null, menuCommand);

        MenuBar.MenuItem coefficientItem = mainMenuBar.addItem("Расчет коэффициента", null, null);
        coefficientItem.addItem("Среднего балла", null, educationAverageMarkMenuCommand);
        coefficientItem.addItem("Равномерности", null, trainingUniformityCoefMenuCommand);
        coefficientItem.addItem("Профессиональной перспективы", null, professionalProspectsMenuCommand);

        addComponent(mainMenuBar);
        setComponentAlignment(mainMenuBar, Alignment.MIDDLE_CENTER);
    }

    private final MenuBar.Command menuCommand = selectedItem ->
            Notification.show("Action " + selectedItem.getText(), Notification.Type.HUMANIZED_MESSAGE);
}
