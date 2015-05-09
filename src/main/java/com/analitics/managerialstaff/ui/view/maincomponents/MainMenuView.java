package com.analitics.managerialstaff.ui.view.maincomponents;

import com.analitics.managerialstaff.ui.view.navigations.commands.DepartmentsMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.commands.EmployeesMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.commands.HomeMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.commands.ManagersMenuCommand;
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
    @Autowired private EmployeesMenuCommand employeesMenuCommand;
    @Autowired private ManagersMenuCommand managersMenuCommand;

    @PostConstruct
    private void initMainMenu() {

        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Главная", null, homeMenuCommand);
        mainMenuBar.addItem("Департаменты", null, departmentsMenuCommand);
        mainMenuBar.addItem("Аттестация", null, menuCommand);
        mainMenuBar.addItem("Сотрудники", null, employeesMenuCommand);
        mainMenuBar.addItem("Руководители", null, managersMenuCommand);
        mainMenuBar.addItem("Отчетность", null, menuCommand);

        addComponent(mainMenuBar);
        setComponentAlignment(mainMenuBar, Alignment.MIDDLE_CENTER);
    }

    private final MenuBar.Command menuCommand = selectedItem ->
            Notification.show("Action " + selectedItem.getText(), Notification.Type.HUMANIZED_MESSAGE);
}
