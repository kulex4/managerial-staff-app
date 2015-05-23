package com.analitics.managerialstaff.ui.presenter.navigations.reporting;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.GenderMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.reporting.GenderView;
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
public class GenderPresenter extends AbstractPresenter<GenderView> implements Serializable {

    @Autowired
    public GenderPresenter(GenderView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onGenderMenuItemSelected(GenderMenuCommand genderMenuCommand) {
        getView().generateChart();
    }
}
