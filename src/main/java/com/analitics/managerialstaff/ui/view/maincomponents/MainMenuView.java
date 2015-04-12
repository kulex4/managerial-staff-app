package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.ui.*;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuView extends VerticalLayout {

    @PostConstruct
    private void init() {

        MenuBar mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Главная", null, menuCommand);
        mainMenuBar.addItem("Департаменты", null, menuCommand);
        mainMenuBar.addItem("Аттестация", null, menuCommand);
        mainMenuBar.addItem("Сотрудники", null, menuCommand);
        mainMenuBar.addItem("Руковолители", null, menuCommand);
        mainMenuBar.addItem("Отчетность", null, menuCommand);

        addComponent(mainMenuBar);
        setComponentAlignment(mainMenuBar, Alignment.MIDDLE_CENTER);
    }

    private final MenuBar.Command menuCommand = selectedItem ->
            Notification.show("Action " + selectedItem.getText(), Notification.Type.HUMANIZED_MESSAGE);
}
