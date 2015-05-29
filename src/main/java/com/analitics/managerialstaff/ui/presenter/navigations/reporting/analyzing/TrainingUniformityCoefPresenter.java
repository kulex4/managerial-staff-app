package com.analitics.managerialstaff.ui.presenter.navigations.reporting.analyzing;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.TrainingUniformityCoefMenuCommand;
import com.analitics.managerialstaff.ui.components.factories.TrainingUniformityCoefFactory;
import com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing.TrainingUniformityCoefView;
import com.analitics.managerialstaff.ui.view.navigations.reporting.analyzing.dto.TrainingUniformityCoefRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;
import java.util.List;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class TrainingUniformityCoefPresenter extends AbstractPresenter<TrainingUniformityCoefView> implements Serializable {

    @Autowired
    private TrainingUniformityCoefFactory trainingUniformityCoefFactory;

    @Autowired
    public TrainingUniformityCoefPresenter(TrainingUniformityCoefView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onTrainingUniformityCoefMenuItemSelected(TrainingUniformityCoefMenuCommand trainingUniformityCoefMenuCommand) {
        List<TrainingUniformityCoefRow> rows = trainingUniformityCoefFactory.generateTableData();
        getView().insertData(rows);
    }
}