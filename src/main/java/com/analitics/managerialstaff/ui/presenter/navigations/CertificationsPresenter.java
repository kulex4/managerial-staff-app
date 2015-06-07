package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.service.certification.CertificationService;
import com.analitics.managerialstaff.backend.service.employee.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationAddEvent;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationDeleteEvent;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationEditEvent;
import com.analitics.managerialstaff.ui.components.events.certifications.CertificationSaveEvent;
import com.analitics.managerialstaff.ui.view.navigations.certification.CertificationsView;
import com.analitics.managerialstaff.ui.components.commands.CertificationsMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.certification.forms.CertificationAddEditForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;

/**
 * @author by nikolai.pashkevich
 */
@VaadinComponent
@VaadinUIScope
public class CertificationsPresenter extends AbstractPresenter<CertificationsView> implements Serializable {

    @Autowired
    private CertificationService certificationService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CertificationAddEditForm certificationAddEditForm;

    @Autowired
    public CertificationsPresenter(CertificationsView view, EventBus.UIEventBus eventBus) {
        super(view, eventBus);
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationsMenuItemSelected(CertificationsMenuCommand certificationsMenuCommand) {
        getView().setCertifications(certificationService.findCertifications());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationAddEvent(CertificationAddEvent event) {
        certificationAddEditForm.setFormEntity(new Certification());
        certificationAddEditForm.setEmployees(employeeService.findEmployees());
        certificationAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationEditEvent(CertificationEditEvent event) {
        certificationAddEditForm.setFormEntity(event.getCertification());
        certificationAddEditForm.setEmployees(employeeService.findEmployees());
        certificationAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationDeleteEvent(CertificationDeleteEvent event) {
        certificationService.remove(event.getCertification());
        getView().deleteCertificationSuccessNotification();
        getView().setCertifications(certificationService.findCertifications());
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationSaveEvent(CertificationSaveEvent event) {
        certificationService.saveOrUpdate(event.getCertification());
        if (event.isNew()) {
            getView().saveCertificationSuccessNotification();
        } else {
            getView().editCertificationSuccessNotification();
        }
        getView().setCertifications(certificationService.findCertifications());
    }
}
