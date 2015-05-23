package com.analitics.managerialstaff.ui.presenter.navigations.reporting;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.AverageAgeMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.reporting.AverageAgeView;
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
public class AverageAgePresenter extends AbstractPresenter<AverageAgeView> implements Serializable {

    @Autowired
    public AverageAgePresenter(AverageAgeView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onAverageAgeMenuItemSelected(AverageAgeMenuCommand averageAgeMenuCommand) {
        getView().generateChart();
    }
}
