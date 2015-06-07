package com.analitics.managerialstaff.ui.view.navigations.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.analitics.managerialstaff.ui.view.navigations.certification.dto.CertificationDTO;
import com.vaadin.navigator.View;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationsView extends View {
    String NAME = "certification";
    void setCertifications(Iterable<CertificationDTO> certifications);
    void emptyCertificationNotification();
    void saveCertificationSuccessNotification();
    void editCertificationSuccessNotification();
    void deleteCertificationSuccessNotification();
}
