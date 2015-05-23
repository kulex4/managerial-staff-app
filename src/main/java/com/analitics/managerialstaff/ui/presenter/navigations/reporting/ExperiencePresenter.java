package com.analitics.managerialstaff.ui.presenter.navigations.reporting;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.ExperienceMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.reporting.ExperienceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class ExperiencePresenter extends AbstractPresenter<ExperienceView> implements Serializable {

    @Autowired
    public ExperiencePresenter(ExperienceView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onExperienceMenuItemSelected(ExperienceMenuCommand experienceMenuCommand) {
        getView().generateChart();
    }
}
