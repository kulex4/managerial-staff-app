package com.analitics.managerialstaff.ui.view.maincomponents;

import com.analitics.managerialstaff.ui.view.navigations.commands.DepartmentsMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.commands.HomeMenuCommand;
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

    @PostConstruct
    private void initMainMenu() {

        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Главная", null, homeMenuCommand);
        mainMenuBar.addItem("Департаменты", null, departmentsMenuCommand);
        mainMenuBar.addItem("Аттестация", null, menuCommand);

        MenuBar.MenuItem employeeMenuItem = mainMenuBar.addItem("Сотрудники", null, null);
        employeeMenuItem.addItem("Список сотрудников", null, menuCommand);
        employeeMenuItem.addItem("Добавить", null, menuCommand);
        employeeMenuItem.addItem("Изменить", null, menuCommand);
        employeeMenuItem.addItem("Удалить", null, menuCommand);

        MenuBar.MenuItem managersMenuItem = mainMenuBar.addItem("Руководители", null, null);
        managersMenuItem.addItem("Список руководителей", null, menuCommand);
        managersMenuItem.addItem("Добавить", null, menuCommand);
        managersMenuItem.addItem("Изменить", null, menuCommand);
        managersMenuItem.addItem("Удалить", null, menuCommand);

        mainMenuBar.addItem("Отчетность", null, menuCommand);

        addComponent(mainMenuBar);
        setComponentAlignment(mainMenuBar, Alignment.MIDDLE_CENTER);
    }

    private final MenuBar.Command menuCommand = selectedItem ->
            Notification.show("Action " + selectedItem.getText(), Notification.Type.HUMANIZED_MESSAGE);
}
