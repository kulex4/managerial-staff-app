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
    @Autowired private ManagersMenuCommand managersMenuCommand;
    @Autowired private EducationMenuCommand educationMenuCommand;
    @Autowired private GenderMenuCommand genderMenuCommand;

    @PostConstruct
    private void initMainMenu() {

        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Главная", null, homeMenuCommand);
        mainMenuBar.addItem("Департаменты", null, departmentsMenuCommand);
        mainMenuBar.addItem("Аттестация", null, certificationsMenuCommand);
        mainMenuBar.addItem("Сотрудники", null, employeesMenuCommand);
        mainMenuBar.addItem("Руководители", null, managersMenuCommand);

        MenuBar.MenuItem reportItem = mainMenuBar.addItem("Отчетность", null, null);
        reportItem.addItem("Образование сотрудников", null, educationMenuCommand);
        reportItem.addItem("Опыт работы", null, menuCommand);
        reportItem.addItem("Гендерная принадлежность", null, genderMenuCommand);
        reportItem.addItem("Возраст сотрудников", null, menuCommand);
        reportItem.addItem("Полный отчет о сотрудниках", null, menuCommand);

        MenuBar.MenuItem coefficientItem = reportItem.addItem("Расчет коэффициента", null, null);
        coefficientItem.addItem("Среднего балла", null, menuCommand);
        coefficientItem.addItem("Равномерности", null, menuCommand);
        coefficientItem.addItem("Профессиональной перспективы", null, menuCommand);

        addComponent(mainMenuBar);
        setComponentAlignment(mainMenuBar, Alignment.MIDDLE_CENTER);
    }

    private final MenuBar.Command menuCommand = selectedItem ->
            Notification.show("Action " + selectedItem.getText(), Notification.Type.HUMANIZED_MESSAGE);
}
