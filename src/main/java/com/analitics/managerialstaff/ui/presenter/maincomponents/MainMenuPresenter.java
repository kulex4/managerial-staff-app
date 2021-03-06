package com.analitics.managerialstaff.ui.presenter.maincomponents;

import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.common.Action;
import com.analitics.managerialstaff.ui.common.StartupFilter;
import com.analitics.managerialstaff.ui.components.events.NavigationRequest;
import com.analitics.managerialstaff.ui.presenter.navigations.CertificationsPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.EmployeesPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.HomePresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.analyzing.ProfessionalProspectsPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.reporting.AverageAgePresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.reporting.EducationPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.reporting.ExperiencePresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.reporting.GenderPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.analyzing.EducationAverageMarkPresenter;
import com.analitics.managerialstaff.ui.presenter.navigations.analyzing.TrainingUniformityCoefPresenter;
import com.analitics.managerialstaff.ui.view.maincomponents.MainMenuView;
import com.analitics.managerialstaff.ui.view.navigations.HomeView;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.Event;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class MainMenuPresenter extends AbstractPresenter<MainMenuView> implements Serializable {

    @Autowired private HomePresenter homePresenter;
    @Autowired private CertificationsPresenter certificationsPresenter;
    @Autowired private EmployeesPresenter employeesPresenter;
    @Autowired private EducationPresenter educationPresenter;
    @Autowired private ExperiencePresenter experiencePresenter;
    @Autowired private GenderPresenter genderPresenter;
    @Autowired private AverageAgePresenter averageAgePresenter;
    @Autowired private EducationAverageMarkPresenter educationAverageMarkPresenter;
    @Autowired private TrainingUniformityCoefPresenter trainingUniformityCoefPresenter;
    @Autowired private ProfessionalProspectsPresenter professionalProspectsPresenter;

    @Autowired
    public MainMenuPresenter(MainMenuView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.SESSION, filter = StartupFilter.class)
    public void onStartup(Event<Action> event) {
         UI.getCurrent().getNavigator().navigateTo(HomeView.NAME);
    }

    @EventBusListenerMethod
    public void onMainMenuSelection(NavigationRequest navigationRequest) {
        UI.getCurrent().getNavigator().navigateTo(navigationRequest.getNavigationState());
    }
}
