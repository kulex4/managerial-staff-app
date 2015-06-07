package com.analitics.managerialstaff.ui.view.navigations.certification;

import com.analitics.managerialstaff.backend.model.Certification;
import com.vaadin.navigator.View;

/**
 * @author by nikolai.pashkevich
 */
public interface CertificationsView extends View {
    String NAME = "certification";
    void setCertifications(Iterable<Certification> certifications);
    void emptyCertificationNotification();
    void saveCertificationSuccessNotification();
    void editCertificationSuccessNotification();
    void deleteCertificationSuccessNotification();
}
