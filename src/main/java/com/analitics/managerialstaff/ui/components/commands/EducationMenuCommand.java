package com.analitics.managerialstaff.ui.components.commands;

import com.analitics.managerialstaff.ui.components.events.NavigationRequest;
import com.analitics.managerialstaff.ui.view.navigations.reporting.EducationView;
import com.vaadin.ui.MenuBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class EducationMenuCommand implements MenuBar.Command {

    @Autowired
    private EventBus.UIEventBus eventBus;

    @Override
    public void menuSelected(MenuBar.MenuItem menuItem) {
        eventBus.publish(this, new NavigationRequest(EducationView.NAME));
        eventBus.publish(this, this);
    }
}
