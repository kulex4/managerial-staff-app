package com.analitics.managerialstaff.ui.presenter.navigations.analyzing;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.commands.EducationAverageMarkMenuCommand;
import com.analitics.managerialstaff.ui.components.factories.EducationAverageMarkFactory;
import com.analitics.managerialstaff.ui.view.navigations.analyzing.EducationAverageMarkView;
import com.analitics.managerialstaff.ui.view.navigations.analyzing.dto.EducationAverageMarkRow;
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
public class EducationAverageMarkPresenter extends AbstractPresenter<EducationAverageMarkView> implements Serializable {

    @Autowired
    private EducationAverageMarkFactory averageMarkFactory;

    @Autowired
    public EducationAverageMarkPresenter(EducationAverageMarkView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod
    private void onEducationAverageMarkMenuItemSelected(EducationAverageMarkMenuCommand educationAverageMarkMenuCommand) {
        List<EducationAverageMarkRow> rows = averageMarkFactory.generateTableData();
        getView().insertData(rows);
    }
}
