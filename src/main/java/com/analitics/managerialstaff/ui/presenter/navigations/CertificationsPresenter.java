package com.analitics.managerialstaff.ui.presenter.navigations;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.backend.service.certification.CertificationService;
import com.analitics.managerialstaff.backend.service.employee.EmployeeService;
import com.analitics.managerialstaff.ui.common.AbstractPresenter;
import com.analitics.managerialstaff.ui.components.events.certifications.*;
import com.analitics.managerialstaff.ui.view.navigations.certification.CertificationsView;
import com.analitics.managerialstaff.ui.components.commands.CertificationsMenuCommand;
import com.analitics.managerialstaff.ui.view.navigations.certification.dto.CertificationDTO;
import com.analitics.managerialstaff.ui.view.navigations.certification.forms.CertificationAddEditForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.VaadinComponent;
import org.vaadin.spring.annotation.VaadinUIScope;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
        getView().setCertifications(convertCertificationsToDTOs(certificationService.findCertifications()));
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onSearchParametersChangedEvent(SearchParametersChangedEvent event) {
        Iterable<Certification> certifications = certificationService.findByYearAndQuarter(event.getYear(), event.getQuarter());
        getView().setCertifications(convertCertificationsToDTOs(certifications));
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationAddEvent(CertificationAddEvent event) {
        certificationAddEditForm.setFormEntity(new Certification());
        certificationAddEditForm.setEmployees(employeeService.findEmployees());
        certificationAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationEditEvent(CertificationEditEvent event) {
        Certification certification = certificationService.findById(event.getCertification().getId());
        certificationAddEditForm.setFormEntity(certification);
        certificationAddEditForm.setEmployees(employeeService.findEmployees());
        certificationAddEditForm.openInModalWindow();
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationDeleteEvent(CertificationDeleteEvent event) {
        Certification certificationToDelete = certificationService.findById(event.getCertification().getId());
        certificationService.remove(certificationToDelete);
        getView().deleteCertificationSuccessNotification();
        getView().setCertifications(convertCertificationsToDTOs(certificationService.findCertifications()));
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    private void onCertificationSaveEvent(CertificationSaveEvent event) {
        certificationService.saveOrUpdate(event.getCertification());
        if (event.isNew()) {
            getView().saveCertificationSuccessNotification();
        } else {
            getView().editCertificationSuccessNotification();
        }
        getView().setCertifications(convertCertificationsToDTOs(certificationService.findCertifications()));
    }

    private List<CertificationDTO> convertCertificationsToDTOs(Iterable<Certification> certifications) {
        List<CertificationDTO> certificationDTOs = new ArrayList<>();
        for (Certification certification : certifications) {
            certificationDTOs.add(new CertificationDTO(certification));
        }
        return certificationDTOs;
    }
}
