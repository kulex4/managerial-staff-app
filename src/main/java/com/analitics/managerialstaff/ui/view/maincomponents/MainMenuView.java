package com.analitics.managerialstaff.ui.view.maincomponents;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;

import javax.annotation.PostConstruct;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuView extends HorizontalLayout implements View {

    private MenuBar mainMenuBar;

    @PostConstruct
    private void init() {

        mainMenuBar = new MenuBar();
        mainMenuBar.addItem("Home1", null, menuCommand);
        mainMenuBar.addItem("Home2", null, menuCommand);
        mainMenuBar.addItem("Home3", null, menuCommand);
        mainMenuBar.addItem("Home4", null, menuCommand);
        mainMenuBar.addItem("Home5", null, menuCommand);
        //mainMenuBar.setWidth("100%");

        addComponent(mainMenuBar);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}

    private final MenuBar.Command menuCommand = new MenuBar.Command() {
        @Override
        public void menuSelected(final MenuBar.MenuItem selectedItem) {
            Notification.show("Action " + selectedItem.getText(),
                    Notification.Type.TRAY_NOTIFICATION);
        }
    };
}
